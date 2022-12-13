package com.grupo5.capliza.services;

import com.grupo5.capliza.dtos.RentCheckoutDto;
import com.grupo5.capliza.dtos.RentDto;
import com.grupo5.capliza.models.Rent;
import com.grupo5.capliza.repositories.CarRepository;
import com.grupo5.capliza.repositories.RentRepository;
import com.grupo5.capliza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RentService {
  @Autowired
  RentRepository rentRepository;
  @Autowired
  CarRepository carRepository;
  @Autowired
  UserRepository userRepository;

  public ResponseEntity<Object> rent(RentDto rentDto){
    var rent = new Rent();
    var rents = rentRepository.findByCheckout(null);
    AtomicBoolean forbidden = new AtomicBoolean(false);
    var userRents = rents.stream().filter(rentItem -> Objects.equals(rentItem.getUser().getId(), rentDto.getUser())).toList();

    rent.setCheckin(Instant.parse(rentDto.getCheckin()).atZone(ZoneId.of("-3")).toInstant());
    rent.setExpectedCheckout(Instant.parse(rentDto.getExpectedCheckout()).atZone(ZoneId.of("-3")).toInstant());

    userRents.forEach(rentItem -> {
      if(rent.getCheckin().isAfter(rentItem.getCheckin()) && rent.getCheckin().isBefore(rentItem.getCheckout())) {
        forbidden.set(true);
      }
    });
    if(forbidden.get()){
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Usuário já possui locação para a data especificada");
    }

    var car = carRepository.findById(rentDto.getCar());
    if(car.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro com id '"+rentDto.getCar()+"' não encontrado!");
    }
    rent.setCar(car.get());

    var user = userRepository.findById(rentDto.getUser());
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id '"+rentDto.getCar()+"' não encontrado!");
    }
    rent.setUser(user.get());

    Double price = car.get().getPrice();
    Long days = Duration.between(rent.getCheckin(), rent.getExpectedCheckout()).toDays();
    Double totalPrice = price * days;

    rent.setTotal_price(totalPrice);
    rent.setCreatedAt(Instant.now());
    return ResponseEntity.ok(rentRepository.save(rent));
  }
  public List<Rent> getRents(){
    return rentRepository.findAll();
  }

  public ResponseEntity<Object> rentCheckout(RentCheckoutDto rentDto){
    var rent = rentRepository.findById(rentDto.getId());
    var checkout = Instant.parse(rentDto.getCheckout()).atZone(ZoneId.of("-3")).toInstant();

    if(rent.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro de locação com id '"+rentDto.getId()+"' não encontrado");
    }
    long days = Duration.between(rent.get().getExpectedCheckout(), checkout).toDays();
    if(days > 0){
      var addPrice = (rent.get().getCar().getPrice() * days) * 1.05;
      rent.get().setTotal_price(rent.get().getTotal_price() + addPrice);
    }
    rent.get().setCheckout(checkout);
    rent.get().setUpdatedAt(Instant.now());
    return ResponseEntity.ok(rentRepository.save(rent.get()));
  }
}

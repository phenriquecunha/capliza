package com.grupo5.capliza.services;

import com.grupo5.capliza.dtos.RentDto;
import com.grupo5.capliza.models.Rent;
import com.grupo5.capliza.repositories.CarRepository;
import com.grupo5.capliza.repositories.RentRepository;
import com.grupo5.capliza.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

@Service
public class RentService {
  @Autowired
  RentRepository rentRepository;
  @Autowired
  CarRepository carRepository;
  @Autowired
  UserRepository userRepository;

  public Object rent(RentDto rentDto){
    var rent = new Rent();
    rent.setCheckin(Instant.parse(rentDto.getCheckin()).atZone(ZoneId.of("-3")).toInstant());
    rent.setCheckout(Instant.parse(rentDto.getExpectedCheckout()).atZone(ZoneId.of("-3")).toInstant());

    var car = carRepository.findById(rentDto.getCar());
    if(car.isEmpty()){
      return "Carro com id '"+rentDto.getCar()+"' não encontrado!";
    }
    rent.setCar(car.get());

    var user = userRepository.findById(rentDto.getUser());
    if(user.isEmpty()){
      return "Usuário com id '"+rentDto.getCar()+"' não encontrado!";
    }
    rent.setUser(user.get());

    Double price = car.get().getPrice();
    Long days = Duration.between(rent.getCheckin(), rent.getCheckout()).toDays();
    Double totalPrice = price * days;

    rent.setTotal_price(totalPrice);
    rent.setCreatedAt(Instant.now());
    return rentRepository.save(rent);
  }
}

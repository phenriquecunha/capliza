package com.grupo5.capliza.services;

import com.grupo5.capliza.models.Car;
import com.grupo5.capliza.models.Rent;
import com.grupo5.capliza.repositories.CarRepository;
import com.grupo5.capliza.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarService {

  @Autowired
  CarRepository carRepository;

  @Autowired
  RentRepository rentRepository;

  public List<Car> rentedCars(){
    var rents = rentRepository.findByCheckout(null);
    return rents.stream().map(Rent::getCar).toList();
  }

  public List<Car> availableCars(){
    var rentedCars = rentedCars();
    var cars = carRepository.findAll();
    List<Car> availableCars = new ArrayList<>(cars);

    cars.forEach(car -> {
      rentedCars.forEach(rented -> {
        if(rented.getId().equals(car.getId())){
          availableCars.remove(car);
        }
      });
    });
    return availableCars;
  }
}

package com.grupo5.capliza.controllers;

import com.grupo5.capliza.dtos.CarDto;
import com.grupo5.capliza.models.Car;
import com.grupo5.capliza.repositories.CarRepository;
import com.grupo5.capliza.services.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/car")
public class CarController {

  @Autowired
  CarRepository carRepository;

  @Autowired
  CarService carService;
  @GetMapping
  public ResponseEntity<Object> getCars(@RequestBody @Nullable Map<String, Double> params){
    var cars = carRepository.findAll();
    if(params == null){
      return ResponseEntity.ok(cars);
    }
    return ResponseEntity.ok(
        cars.stream()
            .filter(car -> car.getPrice() <= params.get("max") && car.getPrice() >= params.get("min"))
            .toList()
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> getCarById(@PathVariable Long id){
    var car = carRepository.findById(id);
    if(car.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro com id '"+id+"' não encontrado");
    }
    return ResponseEntity.ok(car.get());
  }

  @PostMapping
  public ResponseEntity<Object> createCar(@RequestBody CarDto carDto){
    var car = new Car();
    carDto.setBrand(carDto.getBrand().toLowerCase());
    carDto.setModel(carDto.getModel().toLowerCase());
    carDto.setType(carDto.getType().toLowerCase());
    BeanUtils.copyProperties(carDto,car);
    car.setCreatedAt(Instant.now());
    return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(car));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Object> deleteCar(@PathVariable Long id){
    var car = carRepository.findById(id);
    if(car.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro com id '"+id+"' não encontrado");
    }
    carRepository.delete(car.get());
    return ResponseEntity.ok("Carro com id '"+id+"' deletado com sucesso!");
  }

  @PutMapping("{id}")
  public ResponseEntity<Object> updatePrice(@PathVariable Long id, @RequestBody Map<String, Double> body){
    var car = carRepository.findById(id);
    if(car.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro com id '"+id+"' não encontrado");
    }

    car.get().setPrice(body.get("price"));
    car.get().setUpdatedAt(Instant.now());
    return ResponseEntity.ok(carRepository.save(car.get()));
  }

  @GetMapping("type/{type}")
  public ResponseEntity<Object> getCarsByType(@PathVariable String type){
    type = type.toLowerCase();
    return ResponseEntity.ok(carRepository.findAllByType(type));
  }

  @GetMapping("brand/{brand}")
  public ResponseEntity<Object> getCarsByModel(@PathVariable String brand){
    brand = brand.toLowerCase();
    return ResponseEntity.ok(carRepository.findAllByBrand(brand));
  }

  @GetMapping("/rented")
  public ResponseEntity<Object> getRentedCars(){
    return ResponseEntity.ok(carService.rentedCars());
  }

  @GetMapping("/available")
  public ResponseEntity<Object> getAvailableCars(){
    return ResponseEntity.ok(carService.availableCars());
  }

}

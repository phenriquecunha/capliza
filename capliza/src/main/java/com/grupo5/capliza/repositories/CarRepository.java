package com.grupo5.capliza.repositories;

import com.grupo5.capliza.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
  List<Car> findAllByType(String type);
  List<Car> findAllByBrand(String brand);
}

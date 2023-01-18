package com.grupo5.capliza.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CarDto {
  String model;
  String brand;
  String type;
  String plate;
  String year;
  BigDecimal price;
}

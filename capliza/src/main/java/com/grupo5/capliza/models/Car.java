package com.grupo5.capliza.models;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_car")
@Data
public class Car {
  @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false)
  String model;
  @Column(nullable = false)
  String brand;
  @Column(nullable = false)
  String type;
  @Column(nullable = false)
  String plate;
  @Column(nullable = false)
  String year;
  @Column(nullable = false)
  Double price;

  @Column(nullable = false)
  Instant createdAt;
  @Column
  Instant updatedAt;

}

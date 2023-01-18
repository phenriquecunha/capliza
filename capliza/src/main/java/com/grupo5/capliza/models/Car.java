package com.grupo5.capliza.models;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

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
  BigDecimal price;

  @Column(nullable = false)
  Instant createdAt;
  @Column
  Instant updatedAt;

}

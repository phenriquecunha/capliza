package com.grupo5.capliza.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_address")
@Data
public class Address {
  @Id
  @GeneratedValue
  Long id;

  @Column(nullable = false)
  String address;
  @Column(nullable = false)
  String number;
  @Column(nullable = false)
  String zipCode;
  @Column(nullable = false)
  String neighborhood;
  @Column(nullable = false)
  String city;
  @Column(nullable = false)
  String country;
  @Column(nullable = false)
  String state;
}

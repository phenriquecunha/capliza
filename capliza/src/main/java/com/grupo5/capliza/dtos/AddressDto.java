package com.grupo5.capliza.dtos;

import lombok.Data;

@Data
public class AddressDto {
  String address;
  String number;
  String zipCode;
  String neighborhood;
  String city;
  String country;
  String state;
}

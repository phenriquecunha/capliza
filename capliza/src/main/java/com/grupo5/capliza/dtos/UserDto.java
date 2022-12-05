package com.grupo5.capliza.dtos;

import lombok.Data;

@Data
public class UserDto {

  String name;
  String password;
  String cpf;
  String cnh;
  String email;
  String phone;
  AddressDto address;

}

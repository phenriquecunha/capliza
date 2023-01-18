package com.grupo5.capliza.dtos;

import lombok.Data;

@Data
public class RentDto {
  Long user;
  Long car;
  String checkin;
  String expectedCheckout;
}

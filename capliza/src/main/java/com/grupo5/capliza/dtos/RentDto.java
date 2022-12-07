package com.grupo5.capliza.dtos;

import com.grupo5.capliza.models.Car;
import com.grupo5.capliza.models.User;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
public class RentDto {
  Long user;
  Long car;
  String checkin;
  String expectedCheckout;
}

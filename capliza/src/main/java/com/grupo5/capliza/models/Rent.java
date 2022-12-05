package com.grupo5.capliza.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_rent")
@Data
public class Rent {
  @Id
  @GeneratedValue
  Long id;

  @ManyToOne
  User user;
  @ManyToOne
  Car car;
  @Column(nullable = false)
  LocalDateTime checkin;
  @Column(nullable = false)
  LocalDateTime checkout;
  @Column(nullable = false)
  Double total_price;
}

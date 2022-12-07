package com.grupo5.capliza.models;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZonedDateTime;

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
  Double total_price;

  @Column(nullable = false)
  Instant checkin;
  @Column
  Instant checkout;
  @Column(nullable = false)
  Instant expectedCheckout;

  @Column(nullable = false)
  Instant createdAt;
  @Column
  Instant updatedAt;
}

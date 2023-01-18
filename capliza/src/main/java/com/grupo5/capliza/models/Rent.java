package com.grupo5.capliza.models;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

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
  BigDecimal total_price;

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

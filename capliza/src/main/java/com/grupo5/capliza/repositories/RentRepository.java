package com.grupo5.capliza.repositories;

import com.grupo5.capliza.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
  public List<Rent> findByCheckout(Object term);
}

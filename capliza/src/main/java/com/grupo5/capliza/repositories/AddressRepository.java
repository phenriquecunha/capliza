package com.grupo5.capliza.repositories;

import com.grupo5.capliza.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
  public List<Address> findAllByZipCode(String zipCode);
}

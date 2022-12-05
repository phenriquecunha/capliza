package com.grupo5.capliza.services;

import com.grupo5.capliza.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {
  @Autowired
  RentRepository rentRepository;
}

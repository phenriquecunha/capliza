package com.grupo5.capliza.controllers;

import com.grupo5.capliza.dtos.RentDto;
import com.grupo5.capliza.models.Rent;
import com.grupo5.capliza.services.RentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import java.time.Instant;
import java.time.ZoneId;

@RestController
@RequestMapping("/rent")
public class RentController {

  @Autowired
  RentService rentService;

  @PostMapping
  public ResponseEntity<Object> rentACar(@RequestBody RentDto rentDto){
    return ResponseEntity.ok(rentService.rent(rentDto));
  }
}

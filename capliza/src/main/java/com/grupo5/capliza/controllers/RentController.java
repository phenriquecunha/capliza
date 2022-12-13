package com.grupo5.capliza.controllers;

import com.grupo5.capliza.dtos.RentCheckoutDto;
import com.grupo5.capliza.dtos.RentDto;
import com.grupo5.capliza.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent")
public class RentController {

  @Autowired
  RentService rentService;

  @GetMapping
  public ResponseEntity<Object> getRents(){
    return ResponseEntity.ok(rentService.getRents());
  }

  @PostMapping
  public ResponseEntity<Object> rentACar(@RequestBody RentDto rentDto){
    return rentService.rent(rentDto);
  }

  @PostMapping("/checkout")
  public ResponseEntity<Object> rentCheckout(@RequestBody RentCheckoutDto rentDto){
    return rentService.rentCheckout(rentDto);
  }
}

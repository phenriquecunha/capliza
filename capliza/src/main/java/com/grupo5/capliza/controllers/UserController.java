package com.grupo5.capliza.controllers;

import com.grupo5.capliza.dtos.UserDto;
import com.grupo5.capliza.models.Address;
import com.grupo5.capliza.models.User;
import com.grupo5.capliza.repositories.AddressRepository;
import com.grupo5.capliza.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserRepository userRepository;

  @Autowired
  AddressRepository addressRepository;

  @GetMapping
  public ResponseEntity<Object> getUsers(){
    return ResponseEntity.ok().body(userRepository.findAll());
  }

  @GetMapping("/{id}")
  public  ResponseEntity<Object> getUserById(@PathVariable Long id){
    var user = userRepository.findById(id);
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id '"+id+"' não encontrado");
    }
    return ResponseEntity.ok().body(user.get());
  }

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody UserDto userDto){
    var address = new Address();
    BeanUtils.copyProperties(userDto.getAddress(), address);
    var list = addressRepository.findAllByZipCode(userDto.getAddress().getZipCode());
    var user = new User();
    boolean addressExists = false;
    List<Address> addresses = new ArrayList<>();

    user.setName(userDto.getName());
    user.setCnh(userDto.getCnh());
    user.setCpf(userDto.getCpf());
    user.setEmail(userDto.getEmail());
    user.setPhone(userDto.getPhone());
    user.setPassword(userDto.getPassword());

    for (Address item :  list) {
      if (item.getNumber().equals(userDto.getAddress().getNumber())) {
        addressExists = true;
        address = item;
        break;
      }
    }
    if (!addressExists) {
      addressRepository.save(address);
    }
    addresses.add(address);
    user.setAddresses(addresses);
    return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
  }

  @PutMapping
  public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto){

    var user = userRepository.findByCpf(userDto.getCpf());
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não cadastrado no banco de dados");
    }

    var list = addressRepository.findAllByZipCode(userDto.getAddress().getZipCode());
    boolean addressExists = false;
    var address = new Address();
    List<Address> addresses = new ArrayList<>();
    BeanUtils.copyProperties(userDto.getAddress(), address);

    for (Address item :  list) {
      if (item.getNumber().equals(userDto.getAddress().getNumber())) {
        addressExists = true;
        address = item;
        break;
      }
    }
    if (!addressExists) {
      addressRepository.save(address);
    }
    user.get().setName(userDto.getName());
    user.get().setCnh(userDto.getCnh());
    user.get().setCpf(userDto.getCpf());
    user.get().setEmail(userDto.getEmail());
    user.get().setPhone(userDto.getPhone());
    user.get().setPassword(userDto.getPassword());
    addresses.add(address);
    user.get().setAddresses(addresses);
    return ResponseEntity.ok().body(userRepository.save(user.get()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable Long id){
    var user = userRepository.findById(id);
    if(user.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com id '"+id+"' não encontrado");
    }
    userRepository.delete(user.get());
    return ResponseEntity.ok().body("Usuário com id '"+id+"' deletado com sucesso!");
  }
}

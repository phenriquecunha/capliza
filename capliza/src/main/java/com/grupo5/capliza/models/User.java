package com.grupo5.capliza.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
public class User{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Column(nullable = false)
  String name;
  @Column(nullable = false)
  String password;
  @Column(nullable = false, unique = true)
  String cpf;
  @Column(nullable = false, unique = true)
  String cnh;
  @Column(nullable = false, unique = false)
  String email;
  @Column(nullable = false)
  String phone;

  @ManyToMany
  List<Address> addresses;

}

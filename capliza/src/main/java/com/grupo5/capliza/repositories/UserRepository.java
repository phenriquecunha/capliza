package com.grupo5.capliza.repositories;

import com.grupo5.capliza.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByCpf(String cpf);
}

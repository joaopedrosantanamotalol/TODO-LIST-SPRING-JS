package com.example.segundoSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.segundoSpring.entity.user;

// criação do userRepository para o Jpa
public interface userRepository extends JpaRepository<user, Long> {

    // chamada por username
    Optional<user> findByUsername(String username);

}

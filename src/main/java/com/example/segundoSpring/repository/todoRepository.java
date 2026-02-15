package com.example.segundoSpring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.segundoSpring.entity.todo;

public interface todoRepository extends JpaRepository<todo, Long>{}

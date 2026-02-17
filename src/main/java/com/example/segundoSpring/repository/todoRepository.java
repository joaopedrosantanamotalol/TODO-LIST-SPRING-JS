package com.example.segundoSpring.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.segundoSpring.entity.todo;
import com.example.segundoSpring.entity.user;

public interface todoRepository extends JpaRepository<todo, Long>{
    List<todo> findByUsuario(user usuario);
}

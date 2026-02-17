package com.example.segundoSpring.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.segundoSpring.entity.todo;
import com.example.segundoSpring.entity.user;
import com.example.segundoSpring.service.todoService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/todos")
public class todoController {

    private todoService todoService;

    public todoController(todoService todoService) {
        this.todoService = todoService;
    }

    // criar todo para usuário logado
    @PostMapping
    public List<todo> create(@RequestBody todo todo, HttpSession session){

        user usuario = (user) session.getAttribute("usuario");

        if(usuario == null){
            throw new RuntimeException("Usuário não logado");
        }

        return todoService.create(todo, usuario);
    }

    // listar apenas todos do usuário logado
    @GetMapping
    public List<todo> list(HttpSession session){

        user usuario = (user) session.getAttribute("usuario");

        if(usuario == null){
            throw new RuntimeException("Usuário não logado");
        }

        return todoService.listByUser(usuario);
    }

    // update só do dono
    @PutMapping
    public List<todo> update(@RequestBody todo todo, HttpSession session){

        user usuario = (user) session.getAttribute("usuario");

        if(usuario == null){
            throw new RuntimeException("Usuário não logado");
        }

        return todoService.update(todo, usuario);
    }

    // delete só do dono
    @DeleteMapping("/{id}")
    public List<todo> delete(@PathVariable Long id, HttpSession session){

        user usuario = (user) session.getAttribute("usuario");

        if(usuario == null){
            throw new RuntimeException("Usuário não logado");
        }

        return todoService.delete(id, usuario);
    }
}

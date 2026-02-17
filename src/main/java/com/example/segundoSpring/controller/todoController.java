package com.example.segundoSpring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.segundoSpring.entity.todo;
import com.example.segundoSpring.service.todoService;

@RestController
// Atribui a rota /todos
@RequestMapping("/todos")
public class todoController {

private todoService todoService;

// chamada do service
public todoController(todoService todoService) {
    this.todoService = todoService;
}

@PostMapping
List<todo> create(@RequestBody todo todo){
    return todoService.create(todo);
}

@GetMapping
List<todo> list(){
    return todoService.list();
}

@PutMapping()
List<todo> update(@RequestBody todo todo){
    return todoService.update(todo);
}

@DeleteMapping("/{id}")
List<todo> delete(@PathVariable Long id){
    return todoService.delete(id);
}
}

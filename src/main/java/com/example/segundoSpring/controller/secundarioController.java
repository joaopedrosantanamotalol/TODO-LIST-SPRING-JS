package com.example.segundoSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.segundoSpring.entity.todo;
import com.example.segundoSpring.entity.user;
import com.example.segundoSpring.service.todoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pagina")
public class secundarioController {
    
private todoService todoService;


public secundarioController(todoService todoService) {
    this.todoService = todoService;
}

@GetMapping
public String mostrarPagina(Model model, HttpSession session){

    user usuario = (user) session.getAttribute("usuario");

    if (usuario == null){
        return "redirect:/auth/login";
    }

    model.addAttribute("todos", todoService.list());
    return "index";
}

@PostMapping("/realizado")
public String atualizarRealizado(@RequestParam Long id,@RequestParam(required = false) Boolean realizado){

    todoService.atualizarRealizado(id, realizado);
    return "redirect:/pagina";
}

@PostMapping("/criar")
public String criar(todo todo){
    todoService.create(todo);
    return "redirect:/pagina";
}

@PostMapping("/deletar")

public String deletar(@RequestParam Long id){
        todoService.delete(id);
        return "redirect:/pagina";
}

@GetMapping("/segunda")
public String segundaPagina(Model model){
    model.addAttribute("todos", todoService.list());
    return "teste";
}

}

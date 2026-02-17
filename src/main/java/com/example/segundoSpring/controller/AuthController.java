package com.example.segundoSpring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.segundoSpring.entity.user;
import com.example.segundoSpring.service.authService;

import jakarta.servlet.http.HttpSession;

// controlador para o Auth
@Controller
@RequestMapping("/auth")
public class AuthController {

    private authService authService;

    public AuthController(com.example.segundoSpring.service.authService authService) {
        this.authService = authService;
    }

    // mostrar pagina login
    @GetMapping("/login")
    public String loginPage(HttpSession session){
       if(session.getAttribute("usuario") != null){
        return "redirect:/pagina";
    }
    return "login";
    }

    //processa o login
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session){

        user usuario = authService.autenticar(username, password);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            return "redirect:/pagina";
        }
        return "redirect:/auth/login?erro";
    }

    // mostrar pagina de cadastro
    @GetMapping("/cadastro")
    public String cadastroPage(){
        return "cadastro";
    }

    // salvar o cadastro
    @PostMapping("/cadastro")
    public String cadastrar(user user){
            authService.cadastrar(user);
            return "redirect:/auth/login";
    }

    // logout
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/auth/login";
    }

    @GetMapping("/us")
    @ResponseBody
    public List<user> listarUsuarios(){
        return authService.listarUsuarios();
    }
}

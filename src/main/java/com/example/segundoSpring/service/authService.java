package com.example.segundoSpring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;    
import com.example.segundoSpring.entity.user;
import com.example.segundoSpring.repository.userRepository;

@Service
public class authService {

    private userRepository repository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public authService(userRepository repository) {
        this.repository = repository;
    }

    // criação de cadastro do usuario
    public void cadastrar(user user){

        if(repository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("Usuário já existe");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        repository.save(user);
        
    }

    // serviço de autenticação simples por senha
    public user autenticar(String username, String password){
        return repository.findByUsername(username)
        .filter(u -> encoder.matches(password, u.getPassword()))
        .orElse(null);
    }

    public List<user> listarUsuarios(){
        return repository.findAll();
    }

}

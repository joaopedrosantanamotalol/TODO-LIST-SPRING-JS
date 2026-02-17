package com.example.segundoSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// criando entidade user e tabela user
@Entity
@Table(name = "usuarios")
public class user {
    
    // id se gera sozinho
    @Id
    @GeneratedValue
    private long id;

    // userName é unico, não se repete
    @Column(unique = true)
    private String username;

    private String password;

    // id só tem get para exibição
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

}

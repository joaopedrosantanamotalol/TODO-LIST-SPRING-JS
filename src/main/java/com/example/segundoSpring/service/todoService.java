package com.example.segundoSpring.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.segundoSpring.entity.todo;
import com.example.segundoSpring.entity.user;
import com.example.segundoSpring.repository.todoRepository;

//regras de negócio / operações

@Service
public class todoService {
    
    // chamada das operações do banco de dados
    private todoRepository todoRepository;
    
    // construtor para chamada do repository e suas funcionalidades
    public todoService(todoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // create, instancia o todo entidade como tipo, salva o save e passa todo variavel
    public List<todo> create(todo todo){
    todoRepository.save(todo);
        return list();
    }

    // listagem por filtro de prioridade do mais alto pra baixo e nome
    public List<todo> list(){
    Sort sort =  Sort.by("prioridade").descending().and(
        Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    // update dos dados
    public List<todo> update(todo todo){
        todoRepository.save(todo);
        return list();
    }

    // busca a todo pelo Id e deleta
    public List<todo> delete(Long id){
        todoRepository.deleteById(id);
        return list();
    }

    public todo findById(Long id){
    return todoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Todo não encontrado"));
}

    public void atualizarRealizado(Long id, Boolean realizado){
        todo t = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

        t.setRealizado(realizado != null && realizado);
        todoRepository.save(t);
}

    public List<todo> listByUser(user usuario){
        return todoRepository.findByUsuario(usuario);
    }

}

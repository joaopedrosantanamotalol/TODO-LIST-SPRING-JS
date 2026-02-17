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

    // create, instancia o todo entidade como tipo, salva o save e passa todo
    // variavel
    public List<todo> create(todo todo, user usuario) {
        todo.setUsuario(usuario);
        todoRepository.save(todo);
        return listByUser(usuario);
    }

    // listagem por filtro de prioridade do mais alto pra baixo e nome
    public List<todo> list() {
        Sort sort = Sort.by("prioridade").descending().and(
                Sort.by("nome").ascending());
        return todoRepository.findAll(sort);
    }

    // update dos dados
    public List<todo> update(todo todoAtualizado, user usuario) {

        todo t = todoRepository.findById(todoAtualizado.getId())
                .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

        // verifica se pertence ao usuário logado
        if (t.getUsuario().getId() != usuario.getId()) {
            throw new RuntimeException("Acesso negado");
        }

        // atualiza apenas campos permitidos
        t.setNome(todoAtualizado.getNome());
        t.setDescricao(todoAtualizado.getDescricao());
        t.setPrioridade(todoAtualizado.getPrioridade());
        t.setRealizado(todoAtualizado.isRealizado());

        todoRepository.save(t);

        return listByUser(usuario);
    }

    // busca a todo pelo Id e deleta
    public List<todo> delete(Long id, user usuario) {

        todo t = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

        // verifica se pertence ao usuário
        if (t.getUsuario().getId() != usuario.getId()) {
            throw new RuntimeException("Acesso negado");
        }

        todoRepository.delete(t);

        return listByUser(usuario);
    }

    public todo findById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo não encontrado"));
    }

    public void atualizarRealizado(Long id, Boolean realizado, user usuario) {

        todo t = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

        // verifica dono
        if (t.getUsuario().getId() != usuario.getId()) {
            throw new RuntimeException("Acesso negado");
        }

        t.setRealizado(realizado != null && realizado);

        todoRepository.save(t);
    }

    public List<todo> listByUser(user usuario) {
        return todoRepository.findByUsuario(usuario);
    }

}

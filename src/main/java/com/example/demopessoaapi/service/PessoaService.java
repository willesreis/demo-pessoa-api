package com.example.demopessoaapi.service;

import com.example.demopessoaapi.model.Pessoa;
import com.example.demopessoaapi.model.PessoaForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaService {
    Page<Pessoa> findAll(Pageable pageable);
    Page<Pessoa> findByNome(String nome, Pageable pageable);
    void save(PessoaForm form);
    Pessoa findById(Long id);
    void deleteById(Long id);
}

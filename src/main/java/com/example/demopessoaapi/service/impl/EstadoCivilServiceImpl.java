package com.example.demopessoaapi.service.impl;

import java.util.List;

import com.example.demopessoaapi.model.EstadoCivil;
import com.example.demopessoaapi.repository.EstadoCivilJdbcRepository;
import com.example.demopessoaapi.service.EstadoCivilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoCivilServiceImpl implements EstadoCivilService {

    @Autowired
    private EstadoCivilJdbcRepository repository;

    @Override
    public List<EstadoCivil> findAll() {
        return repository.findAll();
    }
    
}

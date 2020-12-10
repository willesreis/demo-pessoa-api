package com.example.demopessoaapi.repository;

import java.util.List;

import com.example.demopessoaapi.mapper.EstadoCivilRowMapper;
import com.example.demopessoaapi.model.EstadoCivil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoCivilJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String ESTADO_CIVIL_TODOS_OS_CAMPOS = 
        "select " +
            "id, " +
            "descricao " +
        "from " +
            "tb_estado_civil " +
        "order by id;";

    public List<EstadoCivil> findAll() {
        return jdbcTemplate.query(ESTADO_CIVIL_TODOS_OS_CAMPOS, new EstadoCivilRowMapper());
    }
}

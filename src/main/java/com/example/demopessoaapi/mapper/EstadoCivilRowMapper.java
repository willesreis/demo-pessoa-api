package com.example.demopessoaapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.demopessoaapi.model.EstadoCivil;

import org.springframework.jdbc.core.RowMapper;

public class EstadoCivilRowMapper implements RowMapper<EstadoCivil> {

    @Override
    public EstadoCivil mapRow(ResultSet rs, int row) throws SQLException {
        EstadoCivil estadoCivil = new EstadoCivil(
            rs.getInt("id"), 
            rs.getString("descricao")
        );
        return estadoCivil;
    }

}

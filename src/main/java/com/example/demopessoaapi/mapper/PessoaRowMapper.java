package com.example.demopessoaapi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.demopessoaapi.model.EstadoCivil;
import com.example.demopessoaapi.model.Pessoa;

import org.springframework.jdbc.core.RowMapper;

public class PessoaRowMapper implements RowMapper<Pessoa> {

    private static final int ESTADO_CIVIL_CASADO = 2;

    @Override
    public Pessoa mapRow(ResultSet rs, int row) throws SQLException {
        Pessoa pessoa = new Pessoa(
            rs.getLong("id"), 
            rs.getString("nome"), 
            new EstadoCivil(
                rs.getInt("id_estado_civil"), 
                rs.getString("descricao_estado_civil")
            ),
            rs.getDate("data_nascimento").toLocalDate()
        );
        if (ESTADO_CIVIL_CASADO == rs.getInt("id_estado_civil")) {
            pessoa.setNomeParceiro(rs.getString("nome_parceiro")); 
            pessoa.setDataNascimentoParceiro(rs.getDate("data_nascimento_parceiro").toLocalDate());
        }
        return pessoa;
    }

}

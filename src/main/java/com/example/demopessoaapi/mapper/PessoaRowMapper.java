package com.example.demopessoaapi.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.example.demopessoaapi.model.EstadoCivil;
import com.example.demopessoaapi.model.Pessoa;

import org.springframework.jdbc.core.RowMapper;

public class PessoaRowMapper implements RowMapper<Pessoa> {

    @Override
    public Pessoa mapRow(ResultSet rs, int row) throws SQLException {
        Pessoa pessoa = new Pessoa(rs.getLong("id"), rs.getString("nome"),
                new EstadoCivil(rs.getInt("id_estado_civil"), rs.getString("descricao_estado_civil")),
                rs.getDate("data_nascimento").toLocalDate());
        pessoa.setNomeParceiro(rs.getString("nome_parceiro"));
        pessoa.setDataNascimentoParceiro(getCheckedDataNascimentoParceiro(rs.getDate("data_nascimento_parceiro")));

        return pessoa;
    }

    private LocalDate getCheckedDataNascimentoParceiro(Date date) {
        return date == null ? null : date.toLocalDate();
    }

}

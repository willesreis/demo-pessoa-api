package com.example.demopessoaapi.repository;

import java.util.Collections;
import java.util.List;

import com.example.demopessoaapi.mapper.PessoaRowMapper;
import com.example.demopessoaapi.model.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String PESSOA_TODOS_OS_CAMPOS = 
        "select " +
            "p.id, " +
            "p.nome, " +
            "ec.id as id_estado_civil, " +
            "ec.descricao as descricao_estado_civil, " +
            "p.data_nascimento, " +
            "p.nome_parceiro, " +
            "p.data_nascimento_parceiro " +
        "from " +
            "tb_pessoa p " +
                "inner join tb_estado_civil ec on ec.id = p.id_estado_civil ";
    private static final String PESSOA_CADASTRO =
        "insert into tb_pessoa(nome, id_estado_civil, data_nascimento, nome_parceiro, data_nascimento_parceiro) " +
        "values(?,?,?,?,?)";
    private static final String PESSOA_EDICAO =
        "update tb_pessoa " +
        "set nome = ?, id_estado_civil = ?, data_nascimento = ?, nome_parceiro = ?, data_nascimento_parceiro = ? " +
        "where id = ?";
    private static final String PESSOA_EXCLUSAO =
        "delete from tb_pessoa " +
        "where id = ?";

    public Page<Pessoa> findAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int fromIndex = currentPage * pageSize;

        String sql = PESSOA_TODOS_OS_CAMPOS + "order by p.id desc ";

        List<Pessoa> pessoas = jdbcTemplate.query(sql, new PessoaRowMapper());
        List<Pessoa> list = null;

        if (pessoas.size() < fromIndex) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(fromIndex + pageSize, pessoas.size());
            list = pessoas.subList(fromIndex, toIndex);
        }

        return new PageImpl<Pessoa>(list, pageable, pessoas.size());
    }

    public Page<Pessoa> findByNomeLike(String nome, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int fromIndex = currentPage * pageSize;

        String sql = PESSOA_TODOS_OS_CAMPOS +
            "where p.nome ilike '%" + nome + "%' " +
            "order by p.id desc;";
        
        List<Pessoa> pessoas = jdbcTemplate.query(sql, new PessoaRowMapper());
        List<Pessoa> list = null;

        if (pessoas.size() < fromIndex) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(fromIndex + pageSize, pessoas.size());
            list = pessoas.subList(fromIndex, toIndex);
        }

        return new PageImpl<Pessoa>(list, pageable, pessoas.size());
    }

	public Pessoa findById(Long id) {
        String sql = PESSOA_TODOS_OS_CAMPOS + "where p.id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PessoaRowMapper());
	}

    public void insert(Pessoa pessoa) {
        jdbcTemplate.update(
            PESSOA_CADASTRO, 
            pessoa.getNome(), 
            pessoa.getEstadoCivil().getId(), 
            pessoa.getDataNascimento(), 
            pessoa.getNomeParceiro(), 
            pessoa.getDataNascimentoParceiro());
    }

    public void update(Pessoa pessoa) {
        jdbcTemplate.update(
            PESSOA_EDICAO, 
            pessoa.getNome(), 
            pessoa.getEstadoCivil().getId(), 
            pessoa.getDataNascimento(), 
            pessoa.getNomeParceiro(), 
            pessoa.getDataNascimentoParceiro(),
            pessoa.getId());
    }

	public void deleteById(Long id) {
		jdbcTemplate.update(PESSOA_EXCLUSAO, id);
	}
}

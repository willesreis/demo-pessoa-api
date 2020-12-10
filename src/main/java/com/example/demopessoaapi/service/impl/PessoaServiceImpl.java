package com.example.demopessoaapi.service.impl;

import com.example.demopessoaapi.exception.DataNascimentoParceiroNaoPreenchidoException;
import com.example.demopessoaapi.exception.NomeParceiroNaoPreenchidoException;
import com.example.demopessoaapi.model.EstadoCivil;
import com.example.demopessoaapi.model.Pessoa;
import com.example.demopessoaapi.model.PessoaForm;
import com.example.demopessoaapi.repository.PessoaJdbcRepository;
import com.example.demopessoaapi.service.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    private static final int ESTADO_CIVIL_CASADO = 2;

    @Autowired
    private PessoaJdbcRepository repository;

    @Override
    public Page<Pessoa> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Pessoa> findByNome(String nome, Pageable pageable) {
        return repository.findByNomeLike(nome, pageable);
    }

    @Override
    public void save(PessoaForm form) {
        if (ESTADO_CIVIL_CASADO == form.getEstadoCivil()) {
            validarNomeParceiroOuDataNascimentoParceiro(form);
        }
    
        Pessoa pessoa = new Pessoa(
            form.getId(), 
            form.getNome(), 
            new EstadoCivil(form.getEstadoCivil()), 
            form.getDataNascimento()
        );
        pessoa.setNomeParceiro(form.getNomeParceiro());
        pessoa.setDataNascimentoParceiro(form.getDataNascimentoParceiro());

        if (pessoa.getId() != null) {
            Pessoa pessoaToEdit = repository.findById(pessoa.getId());
            pessoaToEdit.setNome(pessoa.getNome());
            pessoaToEdit.setEstadoCivil(pessoa.getEstadoCivil());
            pessoaToEdit.setDataNascimento(pessoa.getDataNascimento());
            pessoaToEdit.setNomeParceiro(pessoa.getNomeParceiro());
            pessoaToEdit.setDataNascimentoParceiro(pessoa.getDataNascimentoParceiro());
            repository.update(pessoaToEdit);
        } else {
            repository.insert(pessoa);
        }
    }
    
    @Override
    public Pessoa findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void validarNomeParceiroOuDataNascimentoParceiro(PessoaForm form) {
        if (form.getNomeParceiro().isEmpty()) {
            throw new NomeParceiroNaoPreenchidoException();
        }
        if (form.getDataNascimentoParceiro() == null) {
            throw new DataNascimentoParceiroNaoPreenchidoException();
        }
    }

}

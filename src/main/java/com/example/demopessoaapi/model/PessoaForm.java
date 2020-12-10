package com.example.demopessoaapi.model;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PessoaForm {

    private Long id;
    @Size(min = 5, max = 255)
    @Pattern(regexp = "[A-Za-z\u00C0-\u00FF ]*", message = "somente letras")
    private String nome;
    @NotNull
    private Integer estadoCivil;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String nomeParceiro;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimentoParceiro;

    public PessoaForm(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeParceiro() {
        return nomeParceiro;
    }

    public void setNomeParceiro(String nomeParceiro) {
        this.nomeParceiro = nomeParceiro;
    }

    public LocalDate getDataNascimentoParceiro() {
        return dataNascimentoParceiro;
    }

    public void setDataNascimentoParceiro(LocalDate dataNascimentoParceiro) {
        this.dataNascimentoParceiro = dataNascimentoParceiro;
    }

    @Override
    public String toString() {
        return String.format("PessoaForm [id=%d, nome=%s, estadoCivil=%d, dataNascimento=%s, nomeParceiro=%s, dataNascimentoParceiro=%s]", id, nome, estadoCivil, dataNascimento, nomeParceiro, dataNascimentoParceiro);
    }
}

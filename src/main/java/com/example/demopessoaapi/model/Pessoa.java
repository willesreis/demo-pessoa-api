package com.example.demopessoaapi.model;

import java.time.LocalDate;

public class Pessoa {

    private Long id;
    private String nome;
    private EstadoCivil estadoCivil;
    private LocalDate dataNascimento;
    private String nomeParceiro;
    private LocalDate dataNascimentoParceiro;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, EstadoCivil estadoCivil, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.estadoCivil = estadoCivil;
        this.dataNascimento = dataNascimento;
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

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
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
        return String.format("Pessoa [id=%d, nome=%s, estadoCivil=%s, dataNascimento=%s, nomeParceiro=%s, dataNascimentoParceiro=%s]", id, nome, estadoCivil, dataNascimento, nomeParceiro, dataNascimentoParceiro);
    }

}

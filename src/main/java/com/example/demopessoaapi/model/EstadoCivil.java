package com.example.demopessoaapi.model;

public class EstadoCivil {

    private Integer id;
    private String descricao;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer id) {
        this.id = id;
    }

    public EstadoCivil(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("EstadoCivil [id=%d, descricao=%s]", id, descricao);
    }

}

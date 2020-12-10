package com.example.demopessoaapi.exception;

public class NomeParceiroNaoPreenchidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public NomeParceiroNaoPreenchidoException() {
        super("cadastro.pessoa.nome.parceiro.erro");
    }

}

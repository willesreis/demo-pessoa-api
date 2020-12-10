package com.example.demopessoaapi.exception;

public class DataNascimentoParceiroNaoPreenchidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataNascimentoParceiroNaoPreenchidoException() {
        super("cadastro.pessoa.data.nascimento.parceiro.erro");
    }

}

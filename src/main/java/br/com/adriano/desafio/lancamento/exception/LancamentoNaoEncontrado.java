package br.com.adriano.desafio.lancamento.exception;

import br.com.adriano.desafio.core.exception.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LancamentoNaoEncontrado extends DefaultException {

    public LancamentoNaoEncontrado() {
        super(HttpStatus.NOT_FOUND, "teste");
    }
}

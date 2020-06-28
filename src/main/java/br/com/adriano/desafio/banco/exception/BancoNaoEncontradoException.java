package br.com.adriano.desafio.banco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class BancoNaoEncontradoException extends RuntimeException {
    public BancoNaoEncontradoException(Long id) {
        super("Nenhum banco contrado : " + id);
    }
}

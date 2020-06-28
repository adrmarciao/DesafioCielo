package br.com.adriano.desafio.remessa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Remessa nao encontrada")
public class RemessaNaoEncontrado extends RuntimeException {
}

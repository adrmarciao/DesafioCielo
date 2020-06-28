package br.com.adriano.desafio.core.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DefaultException extends RuntimeException {

    private HttpStatus httpStatus;

    public DefaultException(HttpStatus statusCode, String statusText) {
        super(statusText);
        this.httpStatus = statusCode;
    }
}

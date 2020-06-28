package br.com.adriano.desafio.core.components;

import br.com.adriano.desafio.core.exception.DefaultException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerComponent {

    @ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException(final DefaultException exception) {
        final Map<String, Object> jsonApiErrorAttributes = new LinkedHashMap<>();
        jsonApiErrorAttributes.put(CustomJsonApiErrorComponent.STATUS, exception.getHttpStatus().value());
        jsonApiErrorAttributes.put(CustomJsonApiErrorComponent.TITLE,  exception.getHttpStatus().getReasonPhrase());
        jsonApiErrorAttributes.put(CustomJsonApiErrorComponent.DETAIL, exception.getMessage());

        return new ResponseEntity<>(Map.of(CustomJsonApiErrorComponent.ERRORS, new Map[] {jsonApiErrorAttributes}), exception.getHttpStatus());
    }
}

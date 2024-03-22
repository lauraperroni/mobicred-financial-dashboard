package com.projetofinal.projetofinal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice // Diz que a classe é um advice pro controller
public class RestExceptionHandler {

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(EntityNotFoundException.class) // Diz que é um método que resolve uma exceção
    public static ResponseEntity HandlingErrorEntityNotFound(EntityNotFoundException exception) {
        String msg = exception.getMessage(); // pega a mensagem registrada para mostrar
        var dto = new ExceptionDto(HttpStatus.NOT_FOUND, msg);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }
}

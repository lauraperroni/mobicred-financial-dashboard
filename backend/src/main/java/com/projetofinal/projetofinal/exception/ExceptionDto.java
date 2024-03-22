package com.projetofinal.projetofinal.exception;

import org.springframework.http.HttpStatus;

public record ExceptionDto(
        HttpStatus status,
        String message) {
}

package com.example.ud7productosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<String> mensajeNoEncontrado(ProductoNoEncontradoException ex){
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> mensajeExcepcion(Exception ex){
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ex.getMessage());
    }
}

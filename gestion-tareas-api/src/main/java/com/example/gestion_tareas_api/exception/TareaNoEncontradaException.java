package com.example.gestion_tareas_api.exception;

public class TareaNoEncontradaException extends RuntimeException{
    public TareaNoEncontradaException(String mensaje){
        super(mensaje);
    }
}   

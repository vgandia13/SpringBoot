package com.example.gestion_tareas_api.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}

package com.example.ud7productosapi.exception;

public class ProductoNoEncontradoException extends RuntimeException{
    public ProductoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}

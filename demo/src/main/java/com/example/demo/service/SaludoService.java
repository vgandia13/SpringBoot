package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {

    public List<String> obtenerListaNombres(){
        return List.of("Victor", "Ana", "Luis");
    }

    public String generarSaludo(String nombre) {
        return "Hola " + nombre + ", desde el servicio Spring Boot!";
    }
}

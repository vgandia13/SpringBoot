package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;

@Service
public class UsuarioService {

    public void registrarUsuario(Usuario usuario){
        System.out.println("Usuario procecesado en el servicio: " + usuario.getNombre());
    }
}

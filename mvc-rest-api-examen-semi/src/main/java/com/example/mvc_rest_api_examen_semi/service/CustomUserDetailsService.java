package com.example.mvc_rest_api_examen_semi.service;

import com.example.mvc_rest_api_examen_semi.model.Docente;
import com.example.mvc_rest_api_examen_semi.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Buscamos al docente en la DB por su email
        Docente docente = docenteRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        // 2. Convertimos nuestro 'Docente' en un 'User' que Spring Security entienda
        return User.builder()
                .username(docente.getEmail())
                .password(docente.getPassword()) // Ya está cifrada gracias al registro
                .disabled(!docente.getActivo())  // Si no está activo, no puede entrar
                .roles("DOCENTE")                // Le asignamos un rol por defecto
                .build();
    }
}
package com.example.mvc_rest_api_examen_semi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mvc_rest_api_examen_semi.dto.DocenteDTO;
import com.example.mvc_rest_api_examen_semi.model.Docente;
import com.example.mvc_rest_api_examen_semi.repository.DocenteRepository;
import java.util.ArrayList;

@Service
public class DocenteService {
    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DocenteDTO registrarDocente(DocenteDTO dto){
        Docente d = dtoToEntity(dto);
        d.setPassword(passwordEncoder.encode(d.getPassword()));
        d.setActivo(true); 
        return entityToDto(docenteRepository.save(d));
    }

    // Método fundamental para Spring Security
    public Docente buscarPorEmail(String email) {
        return docenteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado con email: " + email));
    }

    public DocenteDTO entityToDto(Docente d){
        DocenteDTO dto = new DocenteDTO();
        dto.setId(d.getId());
        dto.setNombre(d.getNombre());
        dto.setEmail(d.getEmail());
        dto.setDepartamento(d.getDepartamento());
        dto.setActivo(d.getActivo());
        // Si no tiene reservas, enviamos una lista vacía para evitar errores en la vista
        dto.setReservas(new ArrayList<>()); 
        return dto;
    }

    public Docente dtoToEntity(DocenteDTO dto){
        Docente d = new Docente();
        d.setId(dto.getId());
        d.setNombre(dto.getNombre());
        d.setEmail(dto.getEmail());
        d.setDepartamento(dto.getDepartamento());
        d.setPassword(dto.getPassword());
        d.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        return d;
    }
}
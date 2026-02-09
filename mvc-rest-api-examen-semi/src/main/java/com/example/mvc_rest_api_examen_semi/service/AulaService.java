package com.example.mvc_rest_api_examen_semi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc_rest_api_examen_semi.dto.AulaDTO;
import com.example.mvc_rest_api_examen_semi.model.Aula;
import com.example.mvc_rest_api_examen_semi.repository.AulaRepository;

@Service
public class AulaService {
    @Autowired
    private AulaRepository aulaRepository;

    public List<AulaDTO> listarDisponibles(Boolean disponible) {
        return aulaRepository.findByDisponible(disponible).stream()
                .map(this::entityToDto).toList();
    }

    public AulaDTO buscarPorCodigo(Integer codigo) {
        // Lógica para buscar por el código de aula del examen
        return aulaRepository.findAll().stream()
                .filter(a -> a.getCodigo().equals(codigo))
                .map(this::entityToDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aula no encontrada"));
    }

    public AulaDTO entityToDto(Aula a) {
        return new AulaDTO(a.getId(), a.getCodigo(), a.getCapacidad(), a.getTipo(), a.getDisponible(), new ArrayList<>());
    }
}
package com.example.mvc_rest_api_examen_semi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mvc_rest_api_examen_semi.dto.AulaDTO;
import com.example.mvc_rest_api_examen_semi.service.AulaService;

@RestController
@RequestMapping("/api/aulas")
public class AulaRestController {
    @Autowired
    private AulaService aulaService;

    @GetMapping
    public List<AulaDTO> getAulas(@RequestParam(required = false) Boolean disponible) {
        if (disponible != null) {
            return aulaService.listarDisponibles(disponible);
        }
        // Si no hay filtro, podrías devolver todas o las disponibles por defecto
        return aulaService.listarDisponibles(true);
    }
}

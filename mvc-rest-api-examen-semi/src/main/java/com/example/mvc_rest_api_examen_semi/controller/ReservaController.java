package com.example.mvc_rest_api_examen_semi.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mvc_rest_api_examen_semi.dto.ReservaDTO;
import com.example.mvc_rest_api_examen_semi.model.Docente;
import com.example.mvc_rest_api_examen_semi.service.DocenteService;
import com.example.mvc_rest_api_examen_semi.service.ReservaService;

@Controller
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/reservas")
    public String listarReservas(
            Principal principal, 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha,
            Model model) {
        
        // 1. Obtenemos el email del docente logueado
        String email = principal.getName();
        Docente docente = docenteService.buscarPorEmail(email);

        // 2. Obtenemos sus reservas (filtrando por fecha si existe)
        List<ReservaDTO> reservas;
        if (fecha != null) {
            reservas = reservaService.listarPorDocenteYFechaAnterior(docente, fecha);
        } else {
            reservas = reservaService.listarPorDocente(docente);
        }

        model.addAttribute("reservas", reservas);
        return "reservas"; // Carga reservas.html (que deberías crear siguiendo el estilo de home.html)
    }
    
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
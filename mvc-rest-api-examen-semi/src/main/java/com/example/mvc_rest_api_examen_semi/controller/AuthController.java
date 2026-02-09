package com.example.mvc_rest_api_examen_semi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mvc_rest_api_examen_semi.dto.DocenteDTO;
import com.example.mvc_rest_api_examen_semi.service.DocenteService;

@Controller
public class AuthController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Carga login.html
    }

    @GetMapping("/docentes/registro")
    public String registroForm(Model model) {
        model.addAttribute("docenteDTO", new DocenteDTO());
        return "registro"; // Carga registro.html
    }

    @PostMapping("/docentes/registro")
    public String registrar(@Valid @ModelAttribute("docenteDTO") DocenteDTO dto, 
                            BindingResult result) {
        if (result.hasErrors()) {
            return "registro"; // Si hay errores (email mal, campos vacíos), vuelve al formulario
        }
        docenteService.registrarDocente(dto);
        return "redirect:/login?success"; // Si todo va bien, al login
    }
}

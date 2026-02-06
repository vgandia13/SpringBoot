package com.example.ud6inicial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @GetMapping("/")
    public String mostrarIndex() {
        return "index"; // Esto busca el archivo index.html en la carpeta templates
    }
}

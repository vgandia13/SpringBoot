package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.SaludoService;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class HelloController {

    private final SaludoService saludoService;
    private final UsuarioService usuarioService;

    public HelloController(SaludoService saludoService, UsuarioService usuarioService) {
        this.saludoService = saludoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("personas", saludoService.obtenerListaNombres());
        return "index";
    }   

    @GetMapping("/saluda")
    @ResponseBody
    public String saluda(@RequestParam(defaultValue = "invitado") String nombre) {
        return saludoService.generarSaludo(nombre);
    }

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
    // Es vital pasar un objeto vacío para que Thymeleaf no dé error al cargar el th:object
    model.addAttribute("usuario", new Usuario()); 
    return "registro"; // Debe coincidir exactamente con el nombre del archivo registro.html
    }
    
    @PostMapping("/registro")
    public String procesarFormulario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result) {
        if(result.hasErrors()) {
            return "registro";
        }  
        usuarioService.registrarUsuario(usuario);
        return "redirect:/";     
    }
}

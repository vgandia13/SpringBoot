package com.example.ud7_restaurante.controller.mvc;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/welcome")
    public String welcome(Model model, Principal principal){
        String nombre = principal.getName();
        model.addAttribute("nombre", nombre);
        return "welcome";
    }
}

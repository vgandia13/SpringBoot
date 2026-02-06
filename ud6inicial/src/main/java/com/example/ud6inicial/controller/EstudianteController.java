package com.example.ud6inicial.controller;
import com.example.ud6inicial.service.LibroService;
import org.springframework.stereotype.Controller;

import com.example.ud6inicial.model.Estudiante;
import com.example.ud6inicial.service.EstudianteService;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final LibroService libroService;
    EstudianteService estudianteService;
    
    public EstudianteController(EstudianteService estudianteService, LibroService libroService) {
        this.estudianteService = estudianteService;
        this.libroService = libroService;
    }

    @GetMapping
    public String mostrarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.obtenerListadoEstudiantes());
        return "estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/formulario";
    }
    
    @PostMapping("/nuevo")
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante) {
        estudianteService.guardarEstudiante(estudiante);
        return "redirect:/estudiantes"; // Redirige a la lista de estudiantes después de guardar
    }
    
    @GetMapping("/{id}/libros")
    public String getLibrosEstudiante(@PathVariable Long id, Model model) {
        model.addAttribute("estudiante", estudianteService.obtenerEstudiantePorId(id));
        model.addAttribute("libros", estudianteService.obtenerLibrosEstudiante(id));
        return "estudiantes/libros";
    }

    @GetMapping("/borrar/{id}")
    public String mostrarFormularioBorrado(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("nombre", estudianteService.obtenerEstudiantePorId(id).getNombre());
        return "estudiantes/borrar";
    }

    @PostMapping("/borrar/{id}")
    public String borrarEstudiante(@PathVariable Long id) {
        libroService.eliminarLibrosDeEstudiante(id); // Elimina los libros asociados al estudiante antes de eliminarlo
        estudianteService.eliminarEstudiante(id);
        return "redirect:/estudiantes"; // Redirige a la lista de estudiantes después de borrar
    }
}
package com.example.ud6inicial.controller;

import org.springframework.stereotype.Controller;

import com.example.ud6inicial.model.Estudiante;
import com.example.ud6inicial.model.Libro;
import com.example.ud6inicial.service.EstudianteService;
import com.example.ud6inicial.service.LibroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/libros")
public class LibroController {
    LibroService libroService;
    EstudianteService estudianteService;
    
    public LibroController(LibroService libroService, EstudianteService estudianteService) {
        this.libroService = libroService;
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public String mostrarLibros(Model model) {
    // Añadimos la lista de libros al "modelo" para que el HTML la vea
    model.addAttribute("libros", libroService.obtenerListadoLibros());
    return "libros/lista"; // Esto busca el archivo lista.html en la carpeta libros
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("libro", new Libro());
        return "libros/formulario";
    }
    
    @PostMapping("/nuevo")
    public String guardarLibro(@ModelAttribute Libro libro) {
        libroService.guardarLibro(libro);
        return "redirect:/libros"; // Redirige a la lista de libros después de guardar
    }

    @GetMapping("/ver/{id}")
    public String mostrarLibroPorId(@PathVariable Long id, Model model) {
        Libro libro = libroService.obtenerLibroPorId(id);
        model.addAttribute("libro", libro);
        return "libros/ver";
    }

    @GetMapping("/alquilar/{id}")
    public String formularioAlquiler(@PathVariable Long id, Model model) {
        model.addAttribute("libroId", id);
        model.addAttribute("estudiantes", estudianteService.obtenerListadoEstudiantes());
        return "libros/alquilar";
    }

    @PostMapping("/alquilar/{id}")
    public String alquilarLibro(@PathVariable Long id, @RequestParam Long estudianteId) {
        Libro libro = libroService.obtenerLibroPorId(id);
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(estudianteId);
        libroService.alquilarLibro(libro, estudiante);
        return "redirect:/libros"; // Redirige a la lista de libros después de alquilar
    }
}

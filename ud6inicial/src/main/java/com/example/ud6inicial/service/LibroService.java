package com.example.ud6inicial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ud6inicial.model.Estudiante;
import com.example.ud6inicial.model.Libro;
import com.example.ud6inicial.repository.LibroRepository;

@Service
public class LibroService {
    LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerListadoLibros() {
        return libroRepository.findAll();
    }

    public void guardarLibro(Libro libro) {
        libroRepository.save(libro);
    }

    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro obtenerLibroPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    public void alquilarLibro(Libro libro, Estudiante estudiante) {
        libro.setDisponible(false);
        libro.setEstudiante(estudiante);
        libroRepository.save(libro);
    }

    public void eliminarLibrosDeEstudiante(Long estudianteId) {
        List<Libro> libros = libroRepository.findByEstudianteId(estudianteId);
        for (Libro libro : libros) {
            libro.setDisponible(true);
            libro.setEstudiante(null);
            libroRepository.save(libro);
        }
    }
}

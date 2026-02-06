package com.example.ud6inicial.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ud6inicial.model.Estudiante;
import com.example.ud6inicial.model.Libro;
import com.example.ud6inicial.repository.EstudianteRepository;

@Service
public class EstudianteService {
    EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> obtenerListadoEstudiantes() {
        return estudianteRepository.findAll();
    }

    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    public Estudiante obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public List<Libro> obtenerLibrosEstudiante(Long id) {
        Estudiante estudiante = obtenerEstudiantePorId(id);
        if (estudiante != null) {
            return estudiante.getLibros();
        }
        return List.of(); // Devuelve una lista vacía si el estudiante no existe o no tiene libros
    }
}

package com.example.ud6inicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ud6inicial.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    public Libro findByTitulo(String titulo);
    public java.util.List<Libro> findByEstudianteId(Long estudianteId);
}

package com.example.ud6inicial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ud6inicial.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante , Long> {
    

}

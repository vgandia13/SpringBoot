package com.example.mvc_rest_api_examen_semi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mvc_rest_api_examen_semi.model.Aula;
import java.util.List;


public interface AulaRepository extends JpaRepository<Aula, Long>{
    public List<Aula> findByDisponible(Boolean disponible);
}

package com.example.mvc_rest_api_examen_semi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mvc_rest_api_examen_semi.model.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long>{
    public Optional<Docente> findByEmail(String email);
}

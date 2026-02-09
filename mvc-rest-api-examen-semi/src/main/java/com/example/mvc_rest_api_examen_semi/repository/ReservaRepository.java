package com.example.mvc_rest_api_examen_semi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mvc_rest_api_examen_semi.model.Docente;
import com.example.mvc_rest_api_examen_semi.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Para listar todas las de un docente
    List<Reserva> findByDocente(Docente docente);

    // El que hicimos antes para el filtro de fecha
    List<Reserva> findByFechaReservaBeforeAndDocenteId(LocalDateTime fecha, Long docenteId);

}

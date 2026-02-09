package com.example.gestion_tareas_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_tareas_api.model.Tarea;

public interface TareaRepository extends JpaRepository<Tarea, Long>{
    public List<Tarea> findByUsuarioId(Long usuarioId);
}

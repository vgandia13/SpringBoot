package com.example.gestion_tareas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_tareas_api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}

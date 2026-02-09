package com.example.gestion_tareas_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_tareas_api.dto.TareaDTO;
import com.example.gestion_tareas_api.service.TareaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    TareaService tareaService;

    public TareaController(TareaService tareaService){
        this.tareaService = tareaService;
    }

    @PostMapping("")
    public TareaDTO guardarTarea(@RequestBody @Valid TareaDTO dto, @RequestParam Long idUsuario) {
        return tareaService.guardarTarea(dto, idUsuario);
    }

    @PutMapping("/{id}")
    public TareaDTO actualizarTarea(@PathVariable Long id, @RequestBody @Valid TareaDTO dto) {
        return tareaService.actualizarTarea(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public void borrarTarea(@PathVariable Long id){
        tareaService.borrarTarea(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<TareaDTO> listarTareasPorUsuario(@PathVariable Long idUsuario) {
        return tareaService.listarTareasPorUsuario(idUsuario);
    }
}

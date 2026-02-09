package com.example.gestion_tareas_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gestion_tareas_api.dto.TareaDTO;
import com.example.gestion_tareas_api.exception.TareaNoEncontradaException;
import com.example.gestion_tareas_api.exception.UsuarioNoEncontradoException;
import com.example.gestion_tareas_api.model.Tarea;
import com.example.gestion_tareas_api.repository.TareaRepository;
import com.example.gestion_tareas_api.repository.UsuarioRepository;

@Service
public class TareaService {
    TareaRepository tareaRepository;
    UsuarioRepository usuarioRepository;

    public TareaService(TareaRepository tareaRepository, UsuarioRepository usuarioRepository){
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TareaDTO guardarTarea(TareaDTO dto, Long idUsuario){
        Tarea t = dtoToEntity(dto);
        t.setUsuario(usuarioRepository.findById(idUsuario).orElseThrow(() -> new UsuarioNoEncontradoException("No se encontró usuario con el id introducido.")));
        return entityToDto(tareaRepository.save(t));
    }

    public TareaDTO actualizarTarea(Long id, TareaDTO dto){
        Tarea t = tareaRepository.findById(id).orElseThrow(() -> new TareaNoEncontradaException("No se encontro la tarea con id "+id));
        t.setTitulo(dto.getTitulo());
        t.setDescripcion(dto.getDescripcion());
        t.setCompletado(dto.getCompletado());
        return entityToDto(tareaRepository.save(t));
    }

    public void borrarTarea(Long id){
        if(tareaRepository.existsById(id)){
            tareaRepository.deleteById(id);
        }else{
            throw new TareaNoEncontradaException("No se encontro la tarea con id "+id);
        }
    }

    public List<TareaDTO> listarTareasPorUsuario(Long idUsuario){
        return tareaRepository.findByUsuarioId(idUsuario).stream().map(m -> entityToDto(m)).toList();
    }

    public TareaDTO entityToDto(Tarea t){
        TareaDTO dto = new TareaDTO();
        dto.setId(t.getId());
        dto.setTitulo(t.getTitulo());
        dto.setDescripcion(t.getDescripcion());
        dto.setCompletado(t.getCompletado());
        dto.setUsuarioId(t.getUsuario().getId());
        return dto;
    }

    public Tarea dtoToEntity(TareaDTO dto){
        Tarea t = new Tarea();
        t.setId(dto.getId());
        t.setTitulo(dto.getTitulo());
        t.setDescripcion(dto.getDescripcion());
        t.setCompletado(dto.getCompletado());
        t.setUsuario(usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado.")));
        return t;
    }
}

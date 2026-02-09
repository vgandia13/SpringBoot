package com.example.gestion_tareas_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gestion_tareas_api.dto.UsuarioDTO;
import com.example.gestion_tareas_api.exception.UsuarioNoEncontradoException;
import com.example.gestion_tareas_api.model.Usuario;
import com.example.gestion_tareas_api.repository.UsuarioRepository;

@Service
public class UsuarioService {

    public UsuarioRepository usuarioRepository;
    public TareaService tareaService;

    public UsuarioService(UsuarioRepository usuarioRepository, TareaService tareaService){
        this.usuarioRepository = usuarioRepository;
        this.tareaService = tareaService;
    }

    public UsuarioDTO guardarUsuario(UsuarioDTO dto){
        Usuario u = dtoToEntity(dto);
        return entityToDto(usuarioRepository.save(u));
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id){
        return entityToDto(usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNoEncontradoException("No se encontro el usuario con id "+id)));
    }

    public List<UsuarioDTO> listarUsuarios(){
        return usuarioRepository.findAll().stream().map(m -> entityToDto(m)).toList();
    }

    public void eliminarUsuario(Long id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
        }else{
            throw new UsuarioNoEncontradoException("No se encontro el usuario con id "+id);
        }
    }

    public UsuarioDTO entityToDto(Usuario u){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setListaTareas(
            u.getListaTareas() != null 
            ? u.getListaTareas().stream().map(m -> tareaService.entityToDto(m)).toList()
            : new ArrayList<>()
        );
        return dto;
    }

    public Usuario dtoToEntity(UsuarioDTO dto){
        Usuario u = new Usuario();
        u.setId(dto.getId());
        u.setUsername(dto.getUsername());
        u.setListaTareas(
            dto.getListaTareas() != null 
            ? dto.getListaTareas().stream().map(m -> tareaService.dtoToEntity(m)).toList()
            : new ArrayList<>()
        );
        return u;
    }
}

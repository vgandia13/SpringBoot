package com.example.ud7_restaurante.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ud7_restaurante.dto.UsuarioDTO;
import com.example.ud7_restaurante.exception.ConflictoException;
import com.example.ud7_restaurante.model.Usuario;
import com.example.ud7_restaurante.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registrarUsuario(UsuarioDTO dto){
        Usuario u = dtoToEntity(dto);
        u.setContraseña(passwordEncoder.encode(dto.getContraseña()));
        Optional<Usuario> opt = usuarioRepository.findByUsuario(dto.getUsuario());
        if(opt.isPresent())
            throw new ConflictoException("ya existe un usuario de nombre " + dto.getUsuario());
        
        usuarioRepository.save(u);
    }

    public Usuario dtoToEntity(UsuarioDTO dto){
        return new Usuario
        (
            dto.getId(),
            dto.getUsuario(),
            dto.getContraseña()
        );
    }
}

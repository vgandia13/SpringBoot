package com.example.ud7_restaurante.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ud7_restaurante.exception.RecursoNoEncontradoException;
import com.example.ud7_restaurante.model.Usuario;
import com.example.ud7_restaurante.repository.UsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService{

    private UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> opt = usuarioRepository.findByUsuario(username);

        if(opt.isEmpty())
            throw new RecursoNoEncontradoException("No se ha encontrado el usuario " + username);

        Usuario u = opt.get();

        return User
                    .withUsername(username)
                    .password(u.getContraseña())
                    .build();
    }

}

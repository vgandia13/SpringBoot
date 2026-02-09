package com.example.gestion_tareas_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String username;
    private List<TareaDTO> listaTareas;
}

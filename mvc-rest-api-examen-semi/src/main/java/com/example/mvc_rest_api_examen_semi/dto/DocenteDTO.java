package com.example.mvc_rest_api_examen_semi.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DocenteDTO {
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String departamento;
    @NotBlank
    private String password;
    private Boolean activo = true;
    private List<ReservaDTO> reservas;
}   

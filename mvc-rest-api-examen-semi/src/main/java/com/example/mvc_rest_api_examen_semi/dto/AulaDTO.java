package com.example.mvc_rest_api_examen_semi.dto;

import java.util.List;

import com.example.mvc_rest_api_examen_semi.model.Tipo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AulaDTO {
    private Long id;
    @NotNull
    private Integer codigo;
    private Integer capacidad;
    private Tipo tipo;
    private Boolean disponible;
    private List<ReservaDTO> reservas;
}

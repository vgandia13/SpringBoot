package com.example.mvc_rest_api_examen_semi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ReservaDTO {
    private Long id;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Long docenteId;
    private Integer aulaCodigo;
}

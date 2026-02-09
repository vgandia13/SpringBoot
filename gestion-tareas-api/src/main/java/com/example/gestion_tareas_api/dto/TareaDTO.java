package com.example.gestion_tareas_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TareaDTO {
    private Long id;
    @NotBlank(message = "El titulo no puede estar vacio")
    private String titulo;
    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 10, max = 200)
    private String descripcion;
    @NotNull(message = "Indica si esta completado o no")
    private Boolean completado;
    private Long usuarioId;
}

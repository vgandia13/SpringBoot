package com.example.ud7productosapi.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductoDTO {
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "El codigo no puede estar vacio")
    private Long codigo;
    @NotNull(message = "El precio no puede estar vacio")
    @Positive
    private Double precio;
    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(min = 10)
    private String descripcion;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}

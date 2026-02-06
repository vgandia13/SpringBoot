package com.example.ud7productosapi.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity 
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotNull(message = "El codigo no puede estar vacio")
    private Long codigo;

    @Positive(message = "El precio no puede ser negativo")
    private Double precio;

    @Size(min = 10, message = "La descripcion debe tener al menos 10 caracteres")
    private String descripcion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;
}

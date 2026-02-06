package com.example.ud6inicial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El título no puede estar vacío")
    @Column(unique = true)
    private String titulo;
    private String autor;
    private Integer paginas;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disponible;

    @ManyToOne
    private Estudiante estudiante;
}

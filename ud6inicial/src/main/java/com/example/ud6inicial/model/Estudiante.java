package com.example.ud6inicial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotEmpty(message = "Los apellidos no pueden estar vacíos")
    private String apellidos;
    @NotEmpty(message = "El email no puede estar vacío")
    @Column(unique = true)
    private String email;
    @NotNull(message = "La edad no puede estar vacía")
    private Integer edad;

    @OneToMany(mappedBy = "estudiante")
    private java.util.List<Libro> libros;
}

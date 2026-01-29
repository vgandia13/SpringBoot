package com.example.ud7_restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MesaDTO {

    private Long id;
    private int numMesa;
    private int capacidad; 
    private boolean ocupada;

    private Long[] pedidosIds;
}

package com.example.ud7_restaurante.dto;


import java.time.LocalDateTime;

import com.example.ud7_restaurante.model.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private Estado estado;
    private int total;

    private Long mesaId;
    private Long[] lineasPedidoIds;
}

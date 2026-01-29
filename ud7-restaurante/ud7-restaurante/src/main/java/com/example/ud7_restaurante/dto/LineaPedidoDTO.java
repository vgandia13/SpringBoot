package com.example.ud7_restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineaPedidoDTO {
    private Long id;
    private int cantidad;
    private Long idPedido;
    private Long idProducto;
}

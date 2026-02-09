package com.example.ud7productosapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ud7productosapi.repository.ProductoRepository;
import com.example.ud7productosapi.model.Producto;
import com.example.ud7productosapi.DTO.ProductoDTO;
import com.example.ud7productosapi.exception.ProductoNoEncontradoException;

@Service
public class ProductoService {
    ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO guardarProducto(ProductoDTO dto){
        Producto p = dtoToEntity(dto);
        p.setFechaCreacion(LocalDateTime.now());
        Producto pGuardado = productoRepository.save(p);
        ProductoDTO nuevoDto = entityToDto(pGuardado);
        return nuevoDto;
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto p = productoRepository.findById(id).orElse(null);
        return (p != null) ? entityToDto(p) : null;
    }

    public List<ProductoDTO> obtenerListaProductos(){
        List<Producto> lista = productoRepository.findAll();
        return lista.stream()
                    .map(p -> entityToDto(p))
                    .toList();   
    }

    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDtoNuevo){
        Producto p = productoRepository.findById(id)
        .orElseThrow(() -> new ProductoNoEncontradoException("No existe el producto"));
        p.setNombre(productoDtoNuevo.getNombre());
        p.setCodigo(productoDtoNuevo.getCodigo());
        p.setDescripcion(productoDtoNuevo.getDescripcion());
        p.setPrecio(productoDtoNuevo.getPrecio());
        p.setFechaActualizacion(LocalDateTime.now());
        return entityToDto(productoRepository.save(p));
    }

    public void eliminarProducto(Long id){
        if(!productoRepository.existsById(id)){
            throw new ProductoNoEncontradoException("No se puede eliminar: el producto con ID "+ id + " no existe");
        }
        productoRepository.deleteById(id);
    }

    public Producto dtoToEntity(ProductoDTO productoDto){
        Producto p = new Producto();
        p.setNombre(productoDto.getNombre());
        p.setCodigo(productoDto.getCodigo());
        p.setDescripcion(productoDto.getDescripcion());
        p.setPrecio(productoDto.getPrecio());
        return p;
    }

    public ProductoDTO entityToDto(Producto p){
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setCodigo(p.getCodigo());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setFechaCreacion(p.getFechaCreacion());
        dto.setFechaActualizacion(p.getFechaActualizacion());
        return dto;
    }
}

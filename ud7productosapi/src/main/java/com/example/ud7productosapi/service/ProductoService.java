package com.example.ud7productosapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ud7productosapi.repository.ProductoRepository;
import com.example.ud7productosapi.model.Producto;
import com.example.ud7productosapi.exception.ProductoNoEncontradoException;

@Service
public class ProductoService {
    ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto guardarProducto(Producto producto){
        producto.setFechaCreacion(java.time.LocalDateTime.now());
        return productoRepository.save(producto);
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> obtenerListaProductos(){
        return productoRepository.findAll();
    }

    public Producto actualizarProducto(Long id, Producto productoNuevo){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new ProductoNoEncontradoException("No existe el producto"));

        producto.setDescripcion(productoNuevo.getDescripcion());
        producto.setCodigo(productoNuevo.getCodigo());
        producto.setFechaActualizacion(LocalDateTime.now());
        producto.setNombre(productoNuevo.getNombre());
        producto.setPrecio(productoNuevo.getPrecio());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id){
        if(!productoRepository.existsById(id)){
            throw new ProductoNoEncontradoException("No se puede eliminar: el producto con ID "+ id + "no existe");
        }
        productoRepository.deleteById(id);
    }
}

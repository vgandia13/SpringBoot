package com.example.ud7productosapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ud7productosapi.model.Producto;
import com.example.ud7productosapi.service.ProductoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productoService.guardarProducto(producto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProductoPorId(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.actualizarProducto(id, producto));
    }

    @GetMapping("")
    public ResponseEntity<List<Producto>> obtenerListaProductos() {
        return ResponseEntity.ok(productoService.obtenerListaProductos());
    }
}

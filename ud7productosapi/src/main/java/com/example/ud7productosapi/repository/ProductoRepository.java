package com.example.ud7productosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ud7productosapi.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}

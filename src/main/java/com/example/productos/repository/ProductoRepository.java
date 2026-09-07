package com.example.productos.repository;

import com.example.productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    
     // Consulta JPQL que obtiene todos los productos pertenecientes a una categoria especifica.
     
    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    List<Producto> buscarPorCategoria(@Param("categoria") String categoria);
}

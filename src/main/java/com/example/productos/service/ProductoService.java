package com.example.productos.service;

import com.example.productos.entity.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Registrar (INSERT)
    @Transactional
    public Producto registrar(Producto producto) {
        return productoRepository.save(producto);
    }

    // Consulta JPQL por categoria
    @Transactional(readOnly = true)
    public List<Producto> buscarPorCategoria(String categoria) {
        return productoRepository.buscarPorCategoria(categoria);
    }
}

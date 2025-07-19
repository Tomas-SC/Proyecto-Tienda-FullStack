package com.tomassc.tiendaapi.service;

import com.tomassc.tiendaapi.model.Producto;
import com.tomassc.tiendaapi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    
    @Autowired
    private ProductoRepository productoRepository;

    // Método para obtener todos los productos de la base de datos.
    public List<Producto> getTodosLosProductos() {
        return productoRepository.findAll();
    }
    
    // Método para buscar un producto por su ID.
    public Optional<Producto> getProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Método para guardar un nuevo producto (o actualizar uno existente).
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    // Método para eliminar un producto.
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
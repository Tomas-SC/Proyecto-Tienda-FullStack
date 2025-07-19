package com.tomassc.tiendaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tomassc.tiendaapi.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
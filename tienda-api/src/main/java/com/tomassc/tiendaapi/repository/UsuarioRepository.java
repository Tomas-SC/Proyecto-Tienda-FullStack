package com.tomassc.tiendaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tomassc.tiendaapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
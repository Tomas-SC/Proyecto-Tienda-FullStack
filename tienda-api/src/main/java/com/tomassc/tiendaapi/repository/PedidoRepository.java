package com.tomassc.tiendaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tomassc.tiendaapi.model.Pedido;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

   
    List<Pedido> findByUsuarioId(Long usuarioId);

}
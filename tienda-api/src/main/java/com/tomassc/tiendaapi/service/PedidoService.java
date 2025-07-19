package com.tomassc.tiendaapi.service;

import com.tomassc.tiendaapi.excepciones.StockInsuficienteException;
import com.tomassc.tiendaapi.model.LineaPedido;
import com.tomassc.tiendaapi.model.Pedido;
import com.tomassc.tiendaapi.model.Producto;
import com.tomassc.tiendaapi.model.Usuario;
import com.tomassc.tiendaapi.repository.PedidoRepository;
import com.tomassc.tiendaapi.repository.ProductoRepository;
import com.tomassc.tiendaapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Pedido crearPedido(Pedido pedidoRecibido) throws StockInsuficienteException {
        
        Usuario usuario = usuarioRepository.findById(pedidoRecibido.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + pedidoRecibido.getUsuario().getId()));
        
        double total = 0;

        for (LineaPedido linea : pedidoRecibido.getLineas()) {
            Producto productoEnDB = productoRepository.findById(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + linea.getProducto().getId()));

            if (productoEnDB.getStock() < linea.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para " + productoEnDB.getNombre() +
                        ". Stock disponible: " + productoEnDB.getStock() +
                        ", Cantidad solicitada: " + linea.getCantidad());
            }

            productoEnDB.setStock(productoEnDB.getStock() - linea.getCantidad());
            linea.setProducto(productoEnDB);
            total += linea.getSubtotal();
        }

        pedidoRecibido.setUsuario(usuario);
        pedidoRecibido.setEstado("Pendiente");
        pedidoRecibido.setFecha(LocalDate.now());
        pedidoRecibido.setTotal(total);

        for (LineaPedido linea : pedidoRecibido.getLineas()) {
            linea.setPedido(pedidoRecibido);
        }

        return pedidoRepository.save(pedidoRecibido);
    }
    
    public List<Pedido> getTodosLosPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> getPedidosPorUsuario(Long usuarioId) {
        // Llama al método "PedidoRepository"
        return pedidoRepository.findByUsuarioId(usuarioId);
    }
}
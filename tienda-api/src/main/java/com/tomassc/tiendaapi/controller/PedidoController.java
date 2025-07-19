package com.tomassc.tiendaapi.controller;

import com.tomassc.tiendaapi.excepciones.StockInsuficienteException;
import com.tomassc.tiendaapi.model.Pedido;
import com.tomassc.tiendaapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Endpoint: POST /api/pedidos
    @PostMapping("/pedidos")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
        try {
            Pedido pedidoGuardado = pedidoService.crearPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);
        } catch (StockInsuficienteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    // Endpoint: GET /api/usuarios/{usuarioId}/pedidos
    @GetMapping("/usuarios/{usuarioId}/pedidos")
    public ResponseEntity<List<Pedido>> getPedidosPorUsuario(@PathVariable Long usuarioId) {
       
        List<Pedido> pedidos = pedidoService.getPedidosPorUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/pedidos")
    public List<Pedido> getTodosLosPedidos() {
        return pedidoService.getTodosLosPedidos();
    }
}
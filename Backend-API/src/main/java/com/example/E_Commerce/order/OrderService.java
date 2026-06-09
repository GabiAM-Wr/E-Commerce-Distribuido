package com.example.E_Commerce.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.E_Commerce.services.ReplicationService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ReplicationService replicationService;

    public Order guardar(Order order) {
        // Generar UUID automáticamente
        if (order.getId() == null) {
            order.setId(UUID.randomUUID());
        }
        
        order.setTimestamp(LocalDateTime.now());
        order.setEstado("confirmado");
        order.setNodoOrigen("cassandra-node1");
        
        Order guardado = orderRepository.save(order);
        log.info("✅ Pedido guardado: {} (EVENTUAL CONSISTENCY)", guardado.getId());
        
        // Replicar en otros nodos (Cassandra lo hace automáticamente)
        replicationService.replicarOrden(guardado);
        
        return guardado;
    }

    public List<Order> listarTodos() {
        return orderRepository.findAll();
    }

    public List<Order> listarPorUsuario(Long usuarioId) {
        return orderRepository.findByUsuarioId(usuarioId);
    }

    public void eliminar(UUID id) {
        orderRepository.deleteById(id);
        log.info("🗑️ Pedido eliminado: {}", id);
    }
}

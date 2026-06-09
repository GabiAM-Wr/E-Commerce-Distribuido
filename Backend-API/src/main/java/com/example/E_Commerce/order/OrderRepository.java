package com.example.E_Commerce.order;

import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CassandraRepository<Order, UUID> {
    
    // Búsqueda por usuario
    List<Order> findByUsuarioId(Long usuarioId);
    
    // Búsqueda por estado
    List<Order> findByEstado(String estado);
    
    // Búsqueda por nodo origen (para verificar replicación)
    List<Order> findByNodoOrigen(String nodoOrigen);
}

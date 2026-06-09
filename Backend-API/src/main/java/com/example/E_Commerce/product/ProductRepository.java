package com.example.E_Commerce.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Búsqueda por categoría (SHARDING)
    List<Product> findByCategoria(String categoria);
    
    // Búsqueda por shard (para replicación)
    List<Product> findByShard(String shard);
    
    // Búsqueda por estado de sincronización
    List<Product> findByEstado(String estado);
    
    // Búsqueda por nombre
    Optional<Product> findByNombre(String nombre);
}
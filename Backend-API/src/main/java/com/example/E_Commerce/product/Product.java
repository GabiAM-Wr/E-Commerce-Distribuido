package com.example.E_Commerce.product;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document(collection = "productos")
public class Product {

    @Id
    private String id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String categoria;
    
    // 🆕 Campos para replicación distribuida
    private String shard;                           // En qué shard está (shard_0, shard_1, shard_2)
    private LocalDateTime timestamp;                // Cuándo se creó
    private String estado;                          // "sincronizado", "replicando"
    private Map<String, LocalDateTime> replicaTimestamps; // Timestamps de cada réplica
    private Long version;                           // Versión para consistencia
}

package com.example.E_Commerce.order;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("ordenes")
public class Order {

    @PrimaryKey
    private UUID id;
    
    @Column("usuario_id")
    private Long usuarioId;
    
    @Column("producto_id")
    private String productoId;
    
    @Column("cantidad")
    private int cantidad;
    
    @Column("fecha")
    private String fecha;
    
    // 🆕 Campos para consistencia eventual
    @Column("timestamp")
    private LocalDateTime timestamp;
    
    @Column("estado")
    private String estado;  // "pendiente", "confirmado", "completado"
    
    @Column("nodo_origen")
    private String nodoOrigen; // En qué nodo se creó
}

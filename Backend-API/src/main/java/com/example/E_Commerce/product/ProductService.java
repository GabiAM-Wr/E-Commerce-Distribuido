package com.example.E_Commerce.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.E_Commerce.services.ReplicationService;
import com.example.E_Commerce.services.ShardingService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ReplicationService replicationService;
    private final ShardingService shardingService;

    public Product guardar(Product product) {
        // 1️⃣ Asignar shard basado en categoría
        String shard = shardingService.asignarShard(product.getCategoria());
        product.setShard(shard);
        product.setTimestamp(LocalDateTime.now());
        product.setEstado("sincronizado");
        product.setVersion(1L);
        
        // Inicializar timestamps de réplicas
        Map<String, LocalDateTime> replicas = new HashMap<>();
        replicas.put("mongodb-node1", LocalDateTime.now());
        product.setReplicaTimestamps(replicas);

        // 2️⃣ Guardar en MongoDB
        Product guardado = productRepository.save(product);
        log.info("✅ Producto guardado: {} en {}", guardado.getNombre(), shard);

        // 3️⃣ Disparar replicación (background)
        replicationService.replicarProducto(guardado);

        return guardado;
    }

    public List<Product> listarTodos() {
        return productRepository.findAll();
    }

    public List<Product> listarPorCategoria(String categoria) {
        // SHARDING: Consulta solo el shard correspondiente
        return productRepository.findByCategoria(categoria);
    }

    public Product buscarPorId(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public Map<String, Object> obtenerInfoShard(String id) {
        Product p = productRepository.findById(id).orElse(null);
        if (p == null) return Map.of("error", "Producto no encontrado");
        
        return Map.of(
            "id", id,
            "nombre", p.getNombre(),
            "shard", p.getShard(),
            "categoria", p.getCategoria(),
            "timestamp", p.getTimestamp(),
            "estado", p.getEstado(),
            "version", p.getVersion(),
            "replicaTimestamps", p.getReplicaTimestamps()
        );
    }

    public void eliminar(String id) {
        productRepository.deleteById(id);
        log.info("🗑️ Producto eliminado: {}", id);
    }

    public Product actualizar(String id, Product product) {
        product.setId(id);
        product.setVersion((product.getVersion() != null ? product.getVersion() : 0L) + 1);
        return productRepository.save(product);
    }
}
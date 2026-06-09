package com.example.E_Commerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product crear(@RequestBody Product product) {
        return productService.guardar(product);
    }

    @GetMapping
    public List<Product> listar() {
        return productService.listarTodos();
    }

    @GetMapping("/by-category/{category}")
    public List<Product> porCategoria(@PathVariable String category) {
        return productService.listarPorCategoria(category);
    }

    @GetMapping("/shard-info/{id}")
    public Map<String, Object> infoShard(@PathVariable String id) {
        return productService.obtenerInfoShard(id);
    }

    @GetMapping("/{id}")
    public Product buscar(@PathVariable String id) {
        return productService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Product actualizar(@PathVariable String id, @RequestBody Product product) {
        return productService.actualizar(id, product);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        productService.eliminar(id);
    }
}
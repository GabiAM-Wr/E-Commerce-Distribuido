package com.example.E_Commerce.order;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order crear(@RequestBody Order order) {
        return orderService.guardar(order);
    }

    @GetMapping
    public List<Order> listar() {
        return orderService.listarTodos();
    }

    @GetMapping("/user/{usuarioId}")
    public List<Order> porUsuario(@PathVariable Long usuarioId) {
        return orderService.listarPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        orderService.eliminar(java.util.UUID.fromString(id));
    }
}

package com.example.E_Commerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User crear(@RequestBody User user) {
        return userService.guardar(user);
    }

    @GetMapping
    public List<User> listar() {
        return userService.listarTodos();
    }

    @GetMapping("/{id}")
    public User buscar(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        User usuario = userService.buscarPorEmail(loginRequest.getEmail());

        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas. Verifica tu correo o contraseña.");
        }
    }

    @PutMapping("/{id}")
    public User actualizar(@PathVariable Long id, @RequestBody User user) {
        return userService.actualizar(id, user);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        userService.eliminar(id);
    }
}

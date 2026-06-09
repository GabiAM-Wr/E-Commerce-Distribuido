package com.example.E_Commerce.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User guardar(User user) {
        User guardado = userRepository.save(user);
        log.info("✅ Usuario guardado: {} (STRONG CONSISTENCY)", guardado.getNombre());
        return guardado;
    }

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public User buscarPorId(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User buscarPorEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void eliminar(Long id) {
        userRepository.deleteById(id);
        log.info("🗑️ Usuario eliminado: {}", id);
    }

    public User actualizar(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
}

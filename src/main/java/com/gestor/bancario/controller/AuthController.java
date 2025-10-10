package com.gestor.bancario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> datos) {
        String usuario = datos.get("usuario");
        String password = datos.get("password");
        // Simulación: usuario = "admin", password = "1234"
        if ("admin".equals(usuario) && "1234".equals(password)) {
            return ResponseEntity.ok(Map.of("mensaje", "Login exitoso"));
        } else {
            return ResponseEntity.status(401).body(Map.of("mensaje", "Credenciales incorrectas"));
        }
    }
}

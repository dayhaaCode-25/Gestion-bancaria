package com.gestor.bancario.controller;

import com.gestor.bancario.dto.UsuarioDTO;
import com.gestor.bancario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody @Valid UsuarioDTO usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

        @PutMapping("/{id}")
        public UsuarioDTO actualizarUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioActualizado) {
            return usuarioService.actualizarUsuario(id, usuarioActualizado);
        }

        @DeleteMapping("/{id}")
        public void eliminarUsuario(@PathVariable Long id) {
            usuarioService.eliminarUsuario(id);
        }
}

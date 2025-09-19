package com.gestor.bancario.controller;

import com.gestor.bancario.entity.Transaccion;
import com.gestor.bancario.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    @PostMapping
    public Transaccion registrarTransaccion(@RequestBody Transaccion transaccion) {
        return transaccionService.registrarTransaccion(transaccion);
    }

    @GetMapping
    public List<Transaccion> listarTransacciones() {
        return transaccionService.listarTransacciones();
    }

    @GetMapping("/{id}")
    public Transaccion buscarTransaccionPorId(@PathVariable Long id) {
        return transaccionService.buscarPorId(id);
    }
}

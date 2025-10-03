package com.gestor.bancario.controller;

import com.gestor.bancario.dto.TransaccionDTO;
import com.gestor.bancario.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    /**
     * Para registrar una transacción, usa el campo cuentaId en el JSON:
     * {
     *   "cuentaId": 1,
     *   "tipo": "RETIRO",
     *   "monto": 600.0,
     *   "saldoFinal": null
     * }
     */
    @PostMapping
    public TransaccionDTO registrarTransaccion(@RequestBody @Valid TransaccionDTO transaccion) {
        return transaccionService.registrarTransaccion(transaccion);
    }

    @GetMapping
    public List<TransaccionDTO> listarTransacciones() {
        return transaccionService.listarTransacciones();
    }

    @GetMapping("/{id}")
    public TransaccionDTO buscarTransaccionPorId(@PathVariable Long id) {
        return transaccionService.buscarPorId(id);
    }

        @PutMapping("/{id}")
        public TransaccionDTO actualizarTransaccion(@PathVariable Long id, @RequestBody @Valid TransaccionDTO transaccionActualizada) {
            return transaccionService.actualizarTransaccion(id, transaccionActualizada);
        }

        @DeleteMapping("/{id}")
        public void eliminarTransaccion(@PathVariable Long id) {
            transaccionService.eliminarTransaccion(id);
        }
}

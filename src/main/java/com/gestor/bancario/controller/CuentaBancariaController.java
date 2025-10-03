package com.gestor.bancario.controller;

import com.gestor.bancario.dto.CuentaBancariaDTO;
import jakarta.validation.Valid;
import com.gestor.bancario.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaController {
    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @PostMapping
    public CuentaBancariaDTO crearCuenta(@RequestBody @Valid CuentaBancariaDTO cuenta) {
        return cuentaBancariaService.crearCuenta(cuenta);
    }

    @GetMapping
    public List<CuentaBancariaDTO> listarCuentas() {
        return cuentaBancariaService.listarCuentas();
    }

    @GetMapping("/{id}")
    public CuentaBancariaDTO buscarCuentaPorId(@PathVariable Long id) {
        return cuentaBancariaService.buscarPorId(id);
    }

        @PutMapping("/{id}")
        public CuentaBancariaDTO actualizarCuenta(@PathVariable Long id, @RequestBody @Valid CuentaBancariaDTO cuentaActualizada) {
            return cuentaBancariaService.actualizarCuenta(id, cuentaActualizada);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarCuenta(@PathVariable Long id) {
            cuentaBancariaService.eliminarCuenta(id);
            return ResponseEntity.noContent().build();
        }
}

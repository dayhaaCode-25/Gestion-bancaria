package com.gestor.bancario.controller;

import com.gestor.bancario.entity.CuentaBancaria;
import com.gestor.bancario.service.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaController {
    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @PostMapping
    public CuentaBancaria crearCuenta(@RequestBody CuentaBancaria cuenta) {
        return cuentaBancariaService.crearCuenta(cuenta);
    }

    @GetMapping
    public List<CuentaBancaria> listarCuentas() {
        return cuentaBancariaService.listarCuentas();
    }

    @GetMapping("/{id}")
    public CuentaBancaria buscarCuentaPorId(@PathVariable Long id) {
        return cuentaBancariaService.buscarPorId(id);
    }
}

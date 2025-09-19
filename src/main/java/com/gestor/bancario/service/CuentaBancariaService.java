package com.gestor.bancario.service;

import com.gestor.bancario.entity.CuentaBancaria;
import com.gestor.bancario.repository.CuentaBancariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaBancariaService {
    private final CuentaBancariaRepository cuentaRepository;

    public CuentaBancariaService(CuentaBancariaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public CuentaBancaria crearCuenta(CuentaBancaria cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public List<CuentaBancaria> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public CuentaBancaria buscarPorId(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }
}

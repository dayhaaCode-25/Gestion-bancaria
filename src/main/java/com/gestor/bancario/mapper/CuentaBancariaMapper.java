package com.gestor.bancario.mapper;

import com.gestor.bancario.entity.CuentaBancaria;
import com.gestor.bancario.dto.CuentaBancariaDTO;

public class CuentaBancariaMapper {
    public static CuentaBancariaDTO toDTO(CuentaBancaria cuenta) {
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        dto.setId(cuenta.getId());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setSaldo(cuenta.getSaldo());
        return dto;
    }

    public static CuentaBancaria toEntity(CuentaBancariaDTO dto) {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setId(dto.getId());
        cuenta.setNumeroCuenta(dto.getNumeroCuenta());
        cuenta.setSaldo(dto.getSaldo());
        return cuenta;
    }
}

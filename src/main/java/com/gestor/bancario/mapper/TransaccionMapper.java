package com.gestor.bancario.mapper;

import com.gestor.bancario.entity.Transaccion;
import com.gestor.bancario.dto.TransaccionDTO;

public class TransaccionMapper {
    public static TransaccionDTO toDTO(Transaccion transaccion) {
        TransaccionDTO dto = new TransaccionDTO();
        dto.setId(transaccion.getId());
        dto.setTipo(transaccion.getTipo());
        dto.setMonto(transaccion.getMonto());
        dto.setSaldoFinal(transaccion.getSaldoFinal());
        return dto;
    }

    public static Transaccion toEntity(TransaccionDTO dto) {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(dto.getId());
        transaccion.setTipo(dto.getTipo());
        transaccion.setMonto(dto.getMonto());
        transaccion.setSaldoFinal(dto.getSaldoFinal());
        return transaccion;
    }
}

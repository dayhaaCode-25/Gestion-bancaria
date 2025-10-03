package com.gestor.bancario.service;

import com.gestor.bancario.entity.Transaccion;
import com.gestor.bancario.repository.TransaccionRepository;
import com.gestor.bancario.repository.CuentaBancariaRepository;
import com.gestor.bancario.entity.CuentaBancaria;
import com.gestor.bancario.dto.TransaccionDTO;
import com.gestor.bancario.mapper.TransaccionMapper;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class TransaccionService {
    public TransaccionDTO crearTransaccion(TransaccionDTO transaccionDTO) {
        return registrarTransaccion(transaccionDTO);
    }
    private final TransaccionRepository transaccionRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;

    public TransaccionService(TransaccionRepository transaccionRepository, CuentaBancariaRepository cuentaBancariaRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    public TransaccionDTO registrarTransaccion(TransaccionDTO dto) {
        CuentaBancaria cuenta = null;
        if (dto.getCuentaId() != null) {
            cuenta = cuentaBancariaRepository.findById(dto.getCuentaId())
                .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(dto.getCuentaId(), "CuentaBancaria"));
        }
        // Validación para retiros
        if ("RETIRO".equalsIgnoreCase(dto.getTipo())) {
            if (cuenta == null) {
                throw new RuntimeException("Debe especificar la cuenta para el retiro");
            }
            if (dto.getMonto() > cuenta.getSaldo()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puedes retirar más dinero del que tienes en la cuenta");
            }
            
            cuenta.setSaldo(cuenta.getSaldo() - dto.getMonto());
            cuentaBancariaRepository.save(cuenta);
        }
        Transaccion entidad = TransaccionMapper.toEntity(dto);
        if (cuenta != null) {
            entidad.setCuenta(cuenta);
        }
        Transaccion guardada = transaccionRepository.save(entidad);
        return TransaccionMapper.toDTO(guardada);
    }

    public List<TransaccionDTO> listarTransacciones() {
        return transaccionRepository.findAll()
            .stream()
            .map(TransaccionMapper::toDTO)
            .collect(Collectors.toList());
    }

    public TransaccionDTO buscarPorId(Long id) {
        Transaccion entidad = transaccionRepository.findById(id)
            .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(id, "Transaccion"));
        return TransaccionMapper.toDTO(entidad);
    }

        public TransaccionDTO actualizarTransaccion(Long id, TransaccionDTO dtoActualizada) {
            Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(id, "Transaccion"));

            transaccion.setTipo(dtoActualizada.getTipo());
            transaccion.setMonto(dtoActualizada.getMonto());
            transaccion.setSaldoFinal(dtoActualizada.getSaldoFinal());

            Transaccion actualizada = transaccionRepository.save(transaccion);
            return TransaccionMapper.toDTO(actualizada);
        }

        public void eliminarTransaccion(Long id) {
            Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(id, "Transaccion"));
            transaccionRepository.delete(transaccion);
        }
}

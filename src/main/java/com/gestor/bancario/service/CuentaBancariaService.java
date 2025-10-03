package com.gestor.bancario.service;

import com.gestor.bancario.entity.CuentaBancaria;
import com.gestor.bancario.repository.CuentaBancariaRepository;
import com.gestor.bancario.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.gestor.bancario.dto.CuentaBancariaDTO;
import com.gestor.bancario.mapper.CuentaBancariaMapper;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class CuentaBancariaService {
    private final CuentaBancariaRepository cuentaRepository;
    private final UsuarioRepository usuarioRepository;

    public CuentaBancariaService(CuentaBancariaRepository cuentaRepository, UsuarioRepository usuarioRepository) {
        this.cuentaRepository = cuentaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CuentaBancariaDTO crearCuenta(CuentaBancariaDTO dto) {
        if (cuentaRepository.existsByNumeroCuenta(dto.getNumeroCuenta())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La cuenta con número " + dto.getNumeroCuenta() + " ya existe");
        }
        CuentaBancaria entidad = CuentaBancariaMapper.toEntity(dto);
        if (dto.getUsuarioId() != null) {
            entidad.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado")));
        }
        CuentaBancaria guardada = cuentaRepository.save(entidad);
        return CuentaBancariaMapper.toDTO(guardada);
    }

    public List<CuentaBancariaDTO> listarCuentas() {
        return cuentaRepository.findAll()
            .stream()
            .map(CuentaBancariaMapper::toDTO)
            .collect(Collectors.toList());
    }

    public CuentaBancariaDTO buscarPorId(Long id) {
        CuentaBancaria entidad = cuentaRepository.findById(id)
            .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(id, "CuentaBancaria"));
        return CuentaBancariaMapper.toDTO(entidad);
    }

        public CuentaBancariaDTO actualizarCuenta(Long id, CuentaBancariaDTO dtoActualizada) {
            CuentaBancaria cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(id, "CuentaBancaria"));

            cuenta.setNumeroCuenta(dtoActualizada.getNumeroCuenta());
            cuenta.setSaldo(dtoActualizada.getSaldo());

            CuentaBancaria actualizada = cuentaRepository.save(cuenta);
            return CuentaBancariaMapper.toDTO(actualizada);
        }

        public void eliminarCuenta(Long id) {
            CuentaBancaria cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new com.gestor.bancario.controller.exceptions.DataNotFoundException(id, "CuentaBancaria"));
            cuentaRepository.delete(cuenta);
        }
}

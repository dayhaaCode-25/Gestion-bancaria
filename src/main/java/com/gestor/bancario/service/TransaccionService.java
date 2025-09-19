package com.gestor.bancario.service;

import com.gestor.bancario.entity.Transaccion;
import com.gestor.bancario.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

    public Transaccion registrarTransaccion(Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }

    public Transaccion buscarPorId(Long id) {
        return transaccionRepository.findById(id).orElse(null);
    }
}

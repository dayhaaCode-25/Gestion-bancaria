package com.gestor.bancario.repository;

import com.gestor.bancario.entity.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {
}

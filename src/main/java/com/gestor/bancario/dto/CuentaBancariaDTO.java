
package com.gestor.bancario.dto;

import jakarta.validation.constraints.*;

public class CuentaBancariaDTO {
    private Long usuarioId;
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    private Long id;
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    @Pattern(regexp = "^[0-9]{10,20}$", message = "El número de cuenta debe tener entre 10 y 20 dígitos")
    private String numeroCuenta;

    @NotNull(message = "El saldo no puede ser nulo")
    @Min(value = 0, message = "El saldo no puede ser negativo")
    private Double saldo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}

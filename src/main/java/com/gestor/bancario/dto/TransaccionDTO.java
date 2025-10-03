package com.gestor.bancario.dto;

import jakarta.validation.constraints.*;

public class TransaccionDTO {
    private Long id;
    private Long cuentaId;

    @NotBlank(message = "El tipo de transacción no puede estar vacío")
    @Pattern(regexp = "^(DEPOSITO|RETIRO)$", message = "El tipo debe ser DEPOSITO o RETIRO")
    private String tipo;

    @NotNull(message = "El monto no puede ser nulo")
    @Min(value = 1, message = "El monto debe ser mayor a 0")
    private Double monto;

    private Double saldoFinal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
}

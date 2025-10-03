
package com.gestor.bancario.controller;

import com.gestor.bancario.dto.TransaccionDTO;
import com.gestor.bancario.service.TransaccionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TransaccionControllerTest {
    @Test
    void crearTransaccion_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setCuentaId(1L);
        transaccion.setTipo("DEPOSITO");
        transaccion.setMonto(100.0);
        Mockito.when(service.registrarTransaccion(Mockito.any())).thenReturn(transaccion);
        TransaccionDTO resultado = service.registrarTransaccion(new TransaccionDTO());
        Assertions.assertEquals("DEPOSITO", resultado.getTipo());
        Assertions.assertEquals(100.0, resultado.getMonto());
    }

    @Test
    void eliminarTransaccion_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.doThrow(new RuntimeException("Transaccion con id 99 no existe")).when(service).eliminarTransaccion(99L);
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> service.eliminarTransaccion(99L));
        Assertions.assertEquals("Transaccion con id 99 no existe", ex.getMessage());
    }
}

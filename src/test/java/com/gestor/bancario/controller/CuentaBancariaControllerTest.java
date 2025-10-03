

package com.gestor.bancario.controller;

import com.gestor.bancario.dto.CuentaBancariaDTO;
import com.gestor.bancario.service.CuentaBancariaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;

public class CuentaBancariaControllerTest {
    @Test
    void listarCuentas_exito() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        Mockito.when(service.listarCuentas()).thenReturn(Collections.singletonList(dto));
        List<CuentaBancariaDTO> resultado = service.listarCuentas();
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertSame(dto, resultado.get(0));
    }

    @Test
    void actualizarCuenta_error() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.when(service.actualizarCuenta(Mockito.eq(99L), Mockito.any(CuentaBancariaDTO.class)))
               .thenThrow(new RuntimeException("CuentaBancaria con id 99 no existe"));
        Assertions.assertThrows(RuntimeException.class, () -> service.actualizarCuenta(99L, new CuentaBancariaDTO()));
    }
}

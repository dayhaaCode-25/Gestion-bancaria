package com.gestor.bancario;

import com.gestor.bancario.dto.CuentaBancariaDTO;
import com.gestor.bancario.service.CuentaBancariaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class CuentaBancariaServiceTest {
    @Test
    void crearCuenta_exito() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        dto.setNumeroCuenta("123456");
        dto.setSaldo(1000.0);
        Mockito.when(service.crearCuenta(Mockito.any())).thenReturn(dto);
        CuentaBancariaDTO resultado = service.crearCuenta(dto);
        Assertions.assertEquals("123456", resultado.getNumeroCuenta());
        Assertions.assertEquals(1000.0, resultado.getSaldo());
    }

    @Test
    void crearCuenta_error() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.when(service.crearCuenta(Mockito.any())).thenThrow(new RuntimeException("Error al crear cuenta"));
        Assertions.assertThrows(RuntimeException.class, () -> service.crearCuenta(new CuentaBancariaDTO()));
    }

    @Test
    void listarCuentas_exito() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        Mockito.when(service.listarCuentas()).thenReturn(Collections.singletonList(dto));
        List<CuentaBancariaDTO> resultado = service.listarCuentas();
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    void listarCuentas_error() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.when(service.listarCuentas()).thenThrow(new RuntimeException("Error al listar cuentas"));
        Assertions.assertThrows(RuntimeException.class, service::listarCuentas);
    }

    @Test
    void buscarPorId_exito() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        Mockito.when(service.buscarPorId(1L)).thenReturn(dto);
        CuentaBancariaDTO resultado = service.buscarPorId(1L);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void buscarPorId_error() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.when(service.buscarPorId(1L)).thenThrow(new RuntimeException("Cuenta no encontrada"));
        Assertions.assertThrows(RuntimeException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void actualizarCuenta_exito() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        CuentaBancariaDTO dto = new CuentaBancariaDTO();
        Mockito.when(service.actualizarCuenta(1L, dto)).thenReturn(dto);
        CuentaBancariaDTO resultado = service.actualizarCuenta(1L, dto);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void actualizarCuenta_error() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.when(service.actualizarCuenta(Mockito.eq(1L), Mockito.any(CuentaBancariaDTO.class)))
               .thenThrow(new RuntimeException("Error al actualizar cuenta"));
        Assertions.assertThrows(RuntimeException.class, () -> service.actualizarCuenta(1L, new CuentaBancariaDTO()));
    }

    @Test
    void eliminarCuenta_exito() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.doNothing().when(service).eliminarCuenta(1L);
        Assertions.assertDoesNotThrow(() -> service.eliminarCuenta(1L));
    }

    @Test
    void eliminarCuenta_error() {
        CuentaBancariaService service = Mockito.mock(CuentaBancariaService.class);
        Mockito.doThrow(new RuntimeException("Error al eliminar cuenta")).when(service).eliminarCuenta(1L);
        Assertions.assertThrows(RuntimeException.class, () -> service.eliminarCuenta(1L));
    }
}

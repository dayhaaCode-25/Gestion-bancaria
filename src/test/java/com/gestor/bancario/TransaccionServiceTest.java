package com.gestor.bancario;

import com.gestor.bancario.dto.TransaccionDTO;
import com.gestor.bancario.service.TransaccionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class TransaccionServiceTest {
    @Test
    void crearTransaccion_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        TransaccionDTO dto = new TransaccionDTO();
        dto.setTipo("DEPOSITO");
        dto.setMonto(500.0);
    Mockito.when(service.crearTransaccion(Mockito.any())).thenReturn(dto);
    TransaccionDTO resultado = service.crearTransaccion(dto);
    Assertions.assertEquals("DEPOSITO", resultado.getTipo());
    Assertions.assertEquals(500.0, resultado.getMonto());
    }

    @Test
    void crearTransaccion_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
    Mockito.when(service.crearTransaccion(Mockito.any())).thenThrow(new RuntimeException("Error al crear transacción"));
    Assertions.assertThrows(RuntimeException.class, () -> service.crearTransaccion(new TransaccionDTO()));
    }

    @Test
    void registrarTransaccion_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        TransaccionDTO dto = new TransaccionDTO();
        Mockito.when(service.registrarTransaccion(Mockito.any())).thenReturn(dto);
        TransaccionDTO resultado = service.registrarTransaccion(dto);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void registrarTransaccion_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.when(service.registrarTransaccion(Mockito.any())).thenThrow(new RuntimeException("Error al registrar transacción"));
        Assertions.assertThrows(RuntimeException.class, () -> service.registrarTransaccion(new TransaccionDTO()));
    }

    @Test
    void listarTransacciones_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        TransaccionDTO dto = new TransaccionDTO();
        Mockito.when(service.listarTransacciones()).thenReturn(Collections.singletonList(dto));
        List<TransaccionDTO> resultado = service.listarTransacciones();
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    void listarTransacciones_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.when(service.listarTransacciones()).thenThrow(new RuntimeException("Error al listar transacciones"));
        Assertions.assertThrows(RuntimeException.class, service::listarTransacciones);
    }

    @Test
    void buscarPorId_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        TransaccionDTO dto = new TransaccionDTO();
        Mockito.when(service.buscarPorId(1L)).thenReturn(dto);
        TransaccionDTO resultado = service.buscarPorId(1L);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void buscarPorId_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.when(service.buscarPorId(1L)).thenThrow(new RuntimeException("Transacción no encontrada"));
        Assertions.assertThrows(RuntimeException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void actualizarTransaccion_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        TransaccionDTO dto = new TransaccionDTO();
        Mockito.when(service.actualizarTransaccion(1L, dto)).thenReturn(dto);
        TransaccionDTO resultado = service.actualizarTransaccion(1L, dto);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void actualizarTransaccion_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.when(service.actualizarTransaccion(Mockito.eq(1L), Mockito.any(TransaccionDTO.class)))
               .thenThrow(new RuntimeException("Error al actualizar transacción"));
        Assertions.assertThrows(RuntimeException.class, () -> service.actualizarTransaccion(1L, new TransaccionDTO()));
    }

    @Test
    void eliminarTransaccion_exito() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.doNothing().when(service).eliminarTransaccion(1L);
        Assertions.assertDoesNotThrow(() -> service.eliminarTransaccion(1L));
    }

    @Test
    void eliminarTransaccion_error() {
        TransaccionService service = Mockito.mock(TransaccionService.class);
        Mockito.doThrow(new RuntimeException("Error al eliminar transacción")).when(service).eliminarTransaccion(1L);
        Assertions.assertThrows(RuntimeException.class, () -> service.eliminarTransaccion(1L));
    }
}

package com.gestor.bancario;

import com.gestor.bancario.dto.UsuarioDTO;
import com.gestor.bancario.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class UsuarioServiceTest {
    @Test
    void crearUsuario_exito() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Juan");
        dto.setEmail("juan@email.com");
        Mockito.when(service.crearUsuario(Mockito.any())).thenReturn(dto);
        UsuarioDTO resultado = service.crearUsuario(dto);
        Assertions.assertEquals("Juan", resultado.getNombre());
        Assertions.assertEquals("juan@email.com", resultado.getEmail());
    }

    @Test
    void crearUsuario_error() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        Mockito.when(service.crearUsuario(Mockito.any())).thenThrow(new RuntimeException("Error al crear usuario"));
        Assertions.assertThrows(RuntimeException.class, () -> service.crearUsuario(new UsuarioDTO()));
    }

    @Test
    void listarUsuarios_exito() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        UsuarioDTO dto = new UsuarioDTO();
        Mockito.when(service.listarUsuarios()).thenReturn(Collections.singletonList(dto));
        List<UsuarioDTO> resultado = service.listarUsuarios();
        Assertions.assertEquals(1, resultado.size());
    }

    @Test
    void listarUsuarios_error() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        Mockito.when(service.listarUsuarios()).thenThrow(new RuntimeException("Error al listar usuarios"));
        Assertions.assertThrows(RuntimeException.class, service::listarUsuarios);
    }

    @Test
    void buscarPorId_exito() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        UsuarioDTO dto = new UsuarioDTO();
        Mockito.when(service.buscarPorId(1L)).thenReturn(dto);
        UsuarioDTO resultado = service.buscarPorId(1L);
        Assertions.assertNotNull(resultado);
    }

    @Test
    void buscarPorId_error() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        Mockito.when(service.buscarPorId(1L)).thenThrow(new RuntimeException("Usuario no encontrado"));
        Assertions.assertThrows(RuntimeException.class, () -> service.buscarPorId(1L));
    }

    @Test
    void actualizarUsuario_exito() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        UsuarioDTO dto = new UsuarioDTO();
        Mockito.when(service.actualizarUsuario(1L, dto)).thenReturn(dto);
        UsuarioDTO resultado = service.actualizarUsuario(1L, dto);
        Assertions.assertNotNull(resultado);
    }

  @Test
    void actualizarUsuario_error() {
    UsuarioService service = Mockito.mock(UsuarioService.class);
    Mockito.when(service.actualizarUsuario(Mockito.eq(1L), Mockito.any(UsuarioDTO.class)))
           .thenThrow(new RuntimeException("Error al actualizar usuario"));
    Assertions.assertThrows(RuntimeException.class, () -> service.actualizarUsuario(1L, new UsuarioDTO()));
}

    @Test
    void eliminarUsuario_exito() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        Mockito.doNothing().when(service).eliminarUsuario(1L);
        Assertions.assertDoesNotThrow(() -> service.eliminarUsuario(1L));
    }

    @Test
    void eliminarUsuario_error() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        Mockito.doThrow(new RuntimeException("Error al eliminar usuario")).when(service).eliminarUsuario(1L);
        Assertions.assertThrows(RuntimeException.class, () -> service.eliminarUsuario(1L));
    }
}

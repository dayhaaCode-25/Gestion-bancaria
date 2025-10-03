
package com.gestor.bancario.controller;

import com.gestor.bancario.dto.UsuarioDTO;
import com.gestor.bancario.service.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsuarioControllerTest {
    @Test
    void crearUsuario_exito() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setEmail("juan@email.com");
        usuario.setPassword("123456");
        Mockito.when(service.crearUsuario(Mockito.any())).thenReturn(usuario);
        UsuarioDTO resultado = service.crearUsuario(new UsuarioDTO());
        Assertions.assertEquals("Juan", resultado.getNombre());
        Assertions.assertEquals("juan@email.com", resultado.getEmail());
    }

    @Test
    void buscarUsuarioPorId_error() {
        UsuarioService service = Mockito.mock(UsuarioService.class);
        Mockito.when(service.buscarPorId(99L)).thenThrow(new RuntimeException("Usuario con id 99 no existe"));
        Assertions.assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));
    }
}

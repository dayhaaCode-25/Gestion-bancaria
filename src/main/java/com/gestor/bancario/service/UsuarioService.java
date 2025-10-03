package com.gestor.bancario.service;

import com.gestor.bancario.controller.exceptions.DataNotFoundException;
import com.gestor.bancario.controller.exceptions.DuplicatedDataException;
import com.gestor.bancario.entity.Usuario;
import com.gestor.bancario.repository.UsuarioRepository;
import com.gestor.bancario.dto.UsuarioDTO;
import com.gestor.bancario.mapper.UsuarioMapper;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new DuplicatedDataException("Usuario", usuario.getEmail());
        }
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(guardado);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(id, "Usuario"));
        return UsuarioMapper.toDTO(usuario);
    }

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
            .stream()
            .map(UsuarioMapper::toDTO)
            .collect(Collectors.toList());
    }

        public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO dtoActualizado) {
            Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(id, "Usuario"));

            // Validar email duplicado 
            if (!usuario.getEmail().equals(dtoActualizado.getEmail()) &&
                usuarioRepository.existsByEmail(dtoActualizado.getEmail())) {
                throw new DuplicatedDataException("Usuario", dtoActualizado.getEmail());
            }

            usuario.setNombre(dtoActualizado.getNombre());
            usuario.setEmail(dtoActualizado.getEmail());
            usuario.setPassword(dtoActualizado.getPassword());

            Usuario actualizado = usuarioRepository.save(usuario);
            return UsuarioMapper.toDTO(actualizado);
        }

        public void eliminarUsuario(Long id) {
            Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(id, "Usuario"));
            usuarioRepository.delete(usuario);
        }

}

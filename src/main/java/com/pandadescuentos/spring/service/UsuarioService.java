package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.dto.UsuarioDTO;
import com.pandadescuentos.spring.model.Usuario;
import com.pandadescuentos.spring.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDTO saveUsuario(UsuarioDTO nuevoUsuario) {

        Usuario usuarioParaGuardar = Usuario.builder()
                .nombreUsuario(nuevoUsuario.getNombreUsuario())
                .correo(nuevoUsuario.getCorreoUsuario())
                .contrasena(nuevoUsuario.getContrasena())
                .build();

        usuarioRepository.save(usuarioParaGuardar);
        return nuevoUsuario;
    }
}
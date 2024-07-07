package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.dto.UsuarioDTO;
import com.pandadescuentos.spring.model.Usuario;
import com.pandadescuentos.spring.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public Usuario buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Transactional
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        String encryptedPassword = passwordEncoder.encode(usuario.getContrasena());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDTO saveUsuario(UsuarioDTO nuevoUsuario) {

        Usuario usuarioParaGuardar = Usuario.builder()
                .nombre(nuevoUsuario.getNombre())
                .correo(nuevoUsuario.getCorreo())
                .contrasena(nuevoUsuario.getContrasena())
                .build();

        usuarioRepository.save(usuarioParaGuardar);
        return nuevoUsuario;
    }

    public void registrarUsuario(String nombre, String correo, String contrasena) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setContrasena(passwordEncoder.encode(contrasena)); // Encriptar la contraseña antes de guardarla
        usuarioRepository.save(usuario);
    }


}
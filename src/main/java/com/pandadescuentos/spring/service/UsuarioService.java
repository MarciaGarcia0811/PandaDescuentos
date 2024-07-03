package com.pandadescuentos.spring.service;

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

    @Transactional
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario findById(long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteById(long id) {
        usuarioRepository.deleteById(id);
    }
}
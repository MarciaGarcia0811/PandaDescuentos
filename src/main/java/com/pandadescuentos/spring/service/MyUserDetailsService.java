package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.dto.UsuarioDTO;
import com.pandadescuentos.spring.model.Usuario;
import com.pandadescuentos.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        //Construimos el DTO a partir del cual vamos a generar el usuario autenticado
        UsuarioDTO usuarioAutenticar = UsuarioDTO.builder()
                .correoUsuario(usuario.getCorreo())
                .nombreUsuario(usuario.getNombre())
                .password(usuario.getPassword())
                .nombreRol(usuario.getRolUsuario().getNombreRol()).build()
                .build();

        return usuarioAutenticar;
    }
}
}

package com.pandadescuentos.spring.security;

import com.pandadescuentos.spring.dto.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor

public class UserDetailsImpl {
    private String username;

    private String password;

    private String correo;


    private Collection<? extends GrantedAuthority> authorities;
    //Builder para el objeto UserDetailsImpl, a partir del cual
    public static UserDetailsImpl build(UsuarioDTO usuarioDTO) {
        //Primero creamos una lista de Aauthorities, vacia
        List<GrantedAuthority> authorities = new ArrayList<>();
        //Agregamos a las authorities, una nueva instancia de la clase SimpleGrantedAuthority y obtenemos el nombre del rol que viene el DTO
        authorities.add(new SimpleGrantedAuthority(usuarioDTO.getNombreRol().name()));

        return new UserDetailsImpl(
                usuarioDTO.getNombreUsuario(),
                usuarioDTO.getPassword(),
                usuarioDTO.getCorreoUsuario(),
                authorities);
    }

    //Getter del atributo authority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getCorreo() {
        return correo;
    }


    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

}

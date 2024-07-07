package com.pandadescuentos.spring.controller;

import com.pandadescuentos.spring.model.Usuario;
import com.pandadescuentos.spring.repository.UsuarioRepository;
import com.pandadescuentos.spring.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable long id) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> obtenerDatosUsuario(@AuthenticationPrincipal UserDetails userDetails) {
        String nombreUsuario = userDetails.getUsername();
        Usuario usuario = usuarioService.buscarPorNombre(nombreUsuario);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/buscarPorCorreo")
    public ResponseEntity<?> buscarPorCorreo(@RequestParam String correo) {
        Usuario usuario = usuarioService.buscarPorCorreo(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByCorreo(usuario.getCorreo()) != null ){
            return ResponseEntity.badRequest().body("Email already exists");
        }
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Usuario usuario) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getCorreo(), usuario.getContrasena()));

            Usuario usuarioAutenticado = usuarioService.buscarPorCorreo(usuario.getCorreo());
            return ResponseEntity.ok(usuarioAutenticado);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable long id, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            // Encriptar la contraseña antes de actualizarla, si se ha proporcionado una nueva contraseña
            if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()) {
                String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasena());
                usuario.setContrasena(contrasenaEncriptada);
            } else {
                // Si no se proporcionó una nueva contraseña, mantener la contraseña existente
                usuario.setContrasena(usuarioExistente.getContrasena());
            }

            usuario.setId(id);
            Usuario usuarioActualizado = usuarioService.guardarUsuario(usuario);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable long id) {
        Usuario usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
            usuarioService.eliminarUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
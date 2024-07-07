package com.pandadescuentos.spring.controller;

import com.pandadescuentos.spring.model.Usuario;
import com.pandadescuentos.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
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

    @PostMapping("/agregar")
    public ResponseEntity<Usuario> agregarUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable long id, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioExistente != null) {
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
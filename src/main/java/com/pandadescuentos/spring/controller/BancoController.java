package com.pandadescuentos.spring.controller;


import com.pandadescuentos.spring.config.WebConfig;
import com.pandadescuentos.spring.model.Banco;
import com.pandadescuentos.spring.model.Descuento;
import com.pandadescuentos.spring.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Import(WebConfig.class)
@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public ResponseEntity<List<Banco>> obtenerBancos() {
        List<Banco> bancos = bancoService.obtenerTodosLosBancos();
        return new ResponseEntity<>(bancos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Banco> obtenerBancoPorId(@PathVariable long id) {
        Banco banco = bancoService.obtenerBancoPorId(id);
        if (banco != null) {
            return new ResponseEntity<>(banco, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/agregar")
    public ResponseEntity<Banco> agregarBanco(@RequestBody Banco banco) {
        Banco bancoAgregado = bancoService.agregarBanco(banco);
        return new ResponseEntity<>(bancoAgregado, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarBanco(@RequestParam Long id){
        bancoService.eliminarBanco(id);
        return new ResponseEntity<>("Eliminado exitosamente", HttpStatus.OK);
    }
}

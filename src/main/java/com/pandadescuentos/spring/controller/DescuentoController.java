package com.pandadescuentos.spring.controller;

import com.pandadescuentos.spring.config.WebConfig;
import com.pandadescuentos.spring.model.Banco;
import com.pandadescuentos.spring.model.Descuento;
import com.pandadescuentos.spring.service.DescuentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Import(WebConfig.class)
@RestController
@RequestMapping("/api/descuentos")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    @GetMapping
    public ResponseEntity<List<Descuento>> obtenerDescuentos() {
        List<Descuento> descuentos = descuentoService.obtenerTodosLosDescuentos();
        return new ResponseEntity<>(descuentos, HttpStatus.OK);
    }

}

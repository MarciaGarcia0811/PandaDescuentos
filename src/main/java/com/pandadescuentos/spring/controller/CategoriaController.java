package com.pandadescuentos.spring.controller;

import com.pandadescuentos.spring.model.Categoria;
import com.pandadescuentos.spring.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> obteneCategorias() {
        return categoriaService.obtenerTodasLasCategorias();
    }
}

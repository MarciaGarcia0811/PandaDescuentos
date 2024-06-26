package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.model.Categoria;
import com.pandadescuentos.spring.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerTodosLasCategorias(){
        return  categoriaRepository.findAll();
    }

}

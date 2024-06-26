package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.model.Descuento;
import com.pandadescuentos.spring.repository.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepository;

    public List<Descuento> obtenerTodosLosDescuentos(){
        return  descuentoRepository.findAll();
    }

}

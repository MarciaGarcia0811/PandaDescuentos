package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.model.Descuento;
import com.pandadescuentos.spring.repository.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DescuentoService {

    private final DescuentoRepository descuentoRepository;

    @Autowired
    public DescuentoService(DescuentoRepository descuentoRepository) {
        this.descuentoRepository = descuentoRepository;
    }

    public List<Descuento> findAllDescuentos() {
        return descuentoRepository.findAll();
    }
}

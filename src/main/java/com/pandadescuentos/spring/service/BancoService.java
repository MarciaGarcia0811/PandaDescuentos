package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.model.Banco;
import com.pandadescuentos.spring.repository.BancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public List<Banco> obtenerTodosLosBancos() {
        return bancoRepository.findAll();
    }
}

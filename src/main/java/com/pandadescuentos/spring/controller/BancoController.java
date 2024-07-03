package com.pandadescuentos.spring.controller;


import com.pandadescuentos.spring.config.WebConfig;
import com.pandadescuentos.spring.model.Banco;
import com.pandadescuentos.spring.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Import(WebConfig.class)
@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping
    public List<Banco> obtenerBancos() {
        return bancoService.findAllBancos();
    }
}

package com.pandadescuentos.spring.controller;


import com.pandadescuentos.spring.config.WebConfig;
import com.pandadescuentos.spring.model.Banco;
import com.pandadescuentos.spring.service.BancoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Import(WebConfig.class)
@RestController
@RequestMapping("/bancos")
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @GetMapping("/{id}")
    public Banco obtenerBancoPorId(@PathVariable Long id) {
        var bancoSolicitado = bancoService.findById(id);
        return bancoSolicitado;
    }

    @PostMapping ("/agregar")
    public Banco addBanco(@RequestBody Banco banco){  // el @RequestBody indica que se entregara un objeto en el cuerpo de la peticion
        Banco bancoAgregado = bancoService.agregarBanco(banco);
        return bancoAgregado;

    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarBanco(@RequestParam Long id){
        bancoService.eliminarBanco(id);
        return new ResponseEntity<>("Eliminado exitosamente", HttpStatus.OK);
    }
}

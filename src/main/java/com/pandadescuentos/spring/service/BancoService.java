package com.pandadescuentos.spring.service;

import com.pandadescuentos.spring.model.Banco;
import com.pandadescuentos.spring.repository.BancoRepository;
import jakarta.transaction.Transactional;
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

    public Banco findById(Long id) {
        var banco = bancoRepository.findById(id);
        return banco.get();
    }
    @Transactional
    public Banco agregarBanco(Banco banco){
        return bancoRepository.save(banco);
    }
    @Transactional  //Es void ya que no retornara nada, ya que estamos creando la funcion para eliminar productos
    public void eliminarBanco(Long id){
        bancoRepository.deleteById(id);
    }

}

package com.pandadescuentos.spring.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table( name = "descuentos")
public class Descuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_descuento")
    private Long idDescuento;

    @Column(nullable = false)
    private BigDecimal porcentaje;

    @Column( name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column( name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "banco_id", nullable = false)
    private Banco banco;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

}

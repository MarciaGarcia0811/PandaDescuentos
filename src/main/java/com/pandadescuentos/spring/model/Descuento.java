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
    @Column( name = "id")
    private Long id;

    private String nombre;
    private String slug;
    private String categoria;
    private String logo_url;
    private String portada_url;
    private String opciones_pago;
    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;
    private String vigencia;

    @ManyToOne
    @JoinColumn(name = "banco_id", nullable = false)
    private Banco banco;

}

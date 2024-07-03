package com.pandadescuentos.spring.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "usuarios_descuentos")
public class Usuarios_descuentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "descuento_id", referencedColumnName = "id")
    private Descuento descuento;

    @Column(name = "fecha_aplicacion")
    private Date fechaAplicacion;

    // Getters and setters
    // Constructor(s) if needed

}

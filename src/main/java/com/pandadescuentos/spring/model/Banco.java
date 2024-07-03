package com.pandadescuentos.spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bancos")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(name = "logo", nullable = false)
    private String logo;

}

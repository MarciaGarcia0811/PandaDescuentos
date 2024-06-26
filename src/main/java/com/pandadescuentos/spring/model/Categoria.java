package com.pandadescuentos.spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_categoria")
    private Long IdCategoria;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "logo", nullable = false)
    private String logo;
}

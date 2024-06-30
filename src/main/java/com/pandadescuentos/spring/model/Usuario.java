package com.pandadescuentos.spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private long id;
    @Column
    private String nombre;
    @Column
    private String correo;
    @Column
    private String contrasena;
    @Column
    private Date fechaRegistro;

}
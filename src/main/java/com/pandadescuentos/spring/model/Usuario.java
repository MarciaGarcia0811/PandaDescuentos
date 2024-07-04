package com.pandadescuentos.spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private long id;

    @Column(name = "nombre usuario")
    private String nombreUsuario;

    @Column(name = "correo electronico")
    private String correo;

    @Column(name = "contraseña")
    private String contrasena;

    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "rut")
    private String rut;

    public enum eRol {
        USER,
        GUEST,
        ADMIN,

    }
    @Enumerated(EnumType.STRING)
    private eRol roles;



}
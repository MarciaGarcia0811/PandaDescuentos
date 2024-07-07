package com.pandadescuentos.spring.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo", unique = true)
    private String correo;

    @Column(name = "contrasena")
    private String contrasena;

    @CreationTimestamp
    private Date fechaCreacion;

    public enum eRol {
        USER,
        GUEST,
        ADMIN,
    }

    @Enumerated(EnumType.STRING)
    private eRol roles;
}
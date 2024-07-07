package com.pandadescuentos.spring.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @Pattern(regexp ="^[a-zA-Z]{2,20}$", message = "Campo incorrecto")
    @NotNull(message = "Este campo debe llenarse")//NotNull
    @Size(min = 5, max = 20)
    private String nombre;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotEmpty(message = "El correo no puede estar vacío")
    private String correo;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String contrasena;



}

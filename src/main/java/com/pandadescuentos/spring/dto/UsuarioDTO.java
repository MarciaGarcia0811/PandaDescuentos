package com.pandadescuentos.spring.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data



public class UsuarioDTO {

    @Pattern(regexp ="^[a-zA-Z]{2,20}$", message = "Campo incorrecto")
    @NotNull(message = "Este campo debe llenarse")//NotNull
    @Size(min = 5, max = 20)
    private String nombreUsuario;



    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String CorreoUsuario;


    @NotBlank
    private String contrasena;

}

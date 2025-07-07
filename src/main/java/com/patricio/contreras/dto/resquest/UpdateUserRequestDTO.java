package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequestDTO {


    private String nombres;

    private  String apellidos;

    private String comuna;


    private String telefono;


    private String email;


    private String password;

}

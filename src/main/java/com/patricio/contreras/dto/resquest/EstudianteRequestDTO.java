package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class EstudianteRequestDTO {

  //  private Long id;

    @NotNull(message ="Los nombres son obligatorios")
    private String nombres;

    @NotNull(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotNull(message = "No puede estar vacia la fecha de nacimiento")
    private Date fechaNacimiento;

    @NotNull(message = "El colegio es obligatorio")
    private String colegio;

    @NotNull(message = "El email es obligatorio")
    private  String email;

   /* @NotNull(message = "Usuario ID is mandatory")
    private Long usuarioId;*/

   /* @NotNull(message = "Recorrido ID is madatory")
    private Long recorridoId;*/

}

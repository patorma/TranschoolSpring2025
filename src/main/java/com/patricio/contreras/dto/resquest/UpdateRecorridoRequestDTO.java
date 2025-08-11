package com.patricio.contreras.dto.resquest;


import lombok.Data;

@Data
public class UpdateRecorridoRequestDTO {

    private Long id;

    private String origen;


    private String destino;

    private String descripcion;
}

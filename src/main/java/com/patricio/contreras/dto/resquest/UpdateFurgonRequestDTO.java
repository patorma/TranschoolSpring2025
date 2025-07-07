package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateFurgonRequestDTO {

    private String patente;


    private String descripcion;
}

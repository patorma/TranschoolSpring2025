package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecorridoRequestDTO {

    private Long id;

    @NotNull(message = "no puede estar vacio el origen del recorrido")
    private String origen;

    @NotNull(message = "no puede estar vacio el destino del recorrido")
    private String destino;

    @NotNull(message = "no puede estar vacia la descripción del recorrido")
    private String descripcion;


}

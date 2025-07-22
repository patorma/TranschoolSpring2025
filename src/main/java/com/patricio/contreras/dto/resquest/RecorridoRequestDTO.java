package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecorridoRequestDTO {

    private Long id;

    @NotNull(message = "no puede estar vacia la descripción del recorrido")
    private String descripcion;

    @NotNull(message = "no puede estar vacio el id del estudiante del recorrido")
    private Long estudianteId;
}

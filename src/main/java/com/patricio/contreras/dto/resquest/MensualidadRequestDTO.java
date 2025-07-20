package com.patricio.contreras.dto.resquest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class MensualidadRequestDTO {

    private Long id;

    @NotNull(message = "Monto is mandatory")
    private int monto;

    @NotNull(message = "no puede estar vacia la fecha de vencimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaVencimiento;

    @NotNull(message = "no puede estar vacio el id de usuario")
    private Long usuarioId;
}

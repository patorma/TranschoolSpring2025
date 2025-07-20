package com.patricio.contreras.dto.resquest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateMensualidadRequestDTO {

    private Long id;


    private int monto;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaVencimiento;


    @Enumerated(EnumType.STRING)
    private Estado estado;


}

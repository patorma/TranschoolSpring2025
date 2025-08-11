package com.patricio.contreras.dto.resquest;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateAsignacionDeEstudianteRequestDTO {
    private Long id;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaRegistro;


   /* private Long furgonId;


    private Long estudianteId;*/
}

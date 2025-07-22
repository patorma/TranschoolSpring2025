package com.patricio.contreras.dto.resquest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PagoRequestDTO {

    private Long id;

   /* @NotNull(message = "El monto pagado es obligatorio")
    private int montoPagado;*/

    @NotNull(message = "no puede estar vacia la fecha de pago")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private  Date fechaPago;

    @NotNull(message = "no puede estar vacio el id de la mensualidad")
    private Long mensualidadId;

}

package com.patricio.contreras.dto.resquest;

import com.patricio.contreras.domain.enums.Estado;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class PagoRequestDTO {

    private Long id;

    @NotNull(message = "Monto is mandatory")
    private int monto;

    @NotNull(message = "no puede estar vacia la fecha de vencimiento")
    private Date fechaVencimiento;

    private Date fechaPago;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotNull(message = "no puede estar vacio el id de usuario")
    private Long usuarioId;

}

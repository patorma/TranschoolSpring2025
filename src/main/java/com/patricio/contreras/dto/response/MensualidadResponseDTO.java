package com.patricio.contreras.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Estado;
import com.patricio.contreras.domain.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class MensualidadResponseDTO {

    private Long id;

    private int monto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaVencimiento;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private boolean enabled;

   private UserProfileResponseDTO usuario;




}

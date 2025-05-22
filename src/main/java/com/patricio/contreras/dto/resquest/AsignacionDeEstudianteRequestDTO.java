package com.patricio.contreras.dto.resquest;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsignacionDeEstudianteRequestDTO {

	private Long id;
	
	@NotNull(message = "La fecha de registro es requerida")
	private Date fechaRegistro;
	
	@NotNull(message = "Furgon ID is mandatory")
	private Long furgonId;
	
	@NotNull(message = "Estudiante ID is mandatory")
	private Long estudianteId;
}

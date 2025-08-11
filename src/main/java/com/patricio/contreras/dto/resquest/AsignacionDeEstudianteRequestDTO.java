package com.patricio.contreras.dto.resquest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsignacionDeEstudianteRequestDTO {

	private Long id;
	
	@NotNull(message = "La fecha de registro es requerida")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaRegistro;
	
	@NotNull(message = "Furgon ID is mandatory")
	private Long furgonId;
	
	@NotNull(message = "Estudiante ID is mandatory")
	private Long estudianteId;

	@NotNull(message = "El  ID del recorrido es obligatorio ")
	private Long recorridoId;
	/*	private Recorrido recorrido;*/
}

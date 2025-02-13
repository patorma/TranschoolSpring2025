package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FurgonRequestDTO{

	private Long id;
	
	@NotNull(message = "La patente es requerida")
	private String patente;
	
	@NotNull(message = "La descripcion es requerida")
	private String descripcion;
}

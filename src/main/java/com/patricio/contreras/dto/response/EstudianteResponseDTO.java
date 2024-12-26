package com.patricio.contreras.dto.response;

import lombok.Data;

@Data
public class EstudianteResponseDTO {

	private Long id;
	
	private String nombres;
	
	private String apellidos;
	
	private String colegio;
	
	private String recorridoName;
}

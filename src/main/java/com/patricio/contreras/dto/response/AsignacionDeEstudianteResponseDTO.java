package com.patricio.contreras.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class AsignacionDeEstudianteResponseDTO {

	private Long id;
	
	private Date fechaRegiatro;
	
	private Long estudianteId;
	
	private String estudianteNombres;
	
	private String estudianteApellidos;
	
	private String estudianteColegio;
	
	private String furgonPatente;
	
	private String furgondescripcion;
	
	private String recorridoDescripcion;
	
	
	
	
}

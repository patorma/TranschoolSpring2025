package com.patricio.contreras.dto.response;

import lombok.Data;

@Data
public class RecorridoResponseDTO {
	
	private Long id;
	
	private String descripcion;

	private Long estudianteId;
	private String  estudianteNombres;
	
	private String estudianteApellidos;
	
	

}

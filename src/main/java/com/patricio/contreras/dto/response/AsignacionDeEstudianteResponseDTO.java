package com.patricio.contreras.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.entity.Recorrido;
import lombok.Data;

@Data
public class AsignacionDeEstudianteResponseDTO {

	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaRegistro;
	
	private EstudianteResponseDTO estudiante;
	
   private  FurgonResponseDTO furgon;

	private RecorridoResponseDTO recorrido;
	
	//private String recorridoDescripcion;
	
	
	
	
}

package com.patricio.contreras.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class AlarmaResponseDTO {
	
	private Long id;
	
	private String descripcion;
	
	private String userName;
	
	private String tipoAlarmaName;
	
	private Date fechaAlarma;

}

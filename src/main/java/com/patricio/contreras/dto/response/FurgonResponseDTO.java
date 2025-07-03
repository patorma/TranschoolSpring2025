package com.patricio.contreras.dto.response;

import lombok.Data;

@Data
public class FurgonResponseDTO {

	private Long id;
	
	private String patente;
	
	private String descripcion;

	private boolean enabled;

	private Long usuarioTransportistaId;

	private String usuarioNombres;

	private String usuarioApellidos;

	private String usuarioRole;
}

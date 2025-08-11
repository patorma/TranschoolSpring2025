package com.patricio.contreras.dto.response;

import com.patricio.contreras.domain.enums.Role;
import lombok.Data;

@Data
public class EstudianteResponseDTO {

	private Long id;
	
	private String nombres;
	
	private String apellidos;
	
	private String colegio;

	private  String email;

	private UserProfileResponseDTO usuarioApoderado;
}

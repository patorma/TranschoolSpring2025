package com.patricio.contreras.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Role;
import lombok.Data;

import java.util.Date;

@Data
public class EstudianteResponseDTO {

	private Long id;
	
	private String nombres;
	
	private String apellidos;
	
	private String colegio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaNacimiento;

	private  String email;

	private UserProfileResponseDTO usuarioApoderado;
}

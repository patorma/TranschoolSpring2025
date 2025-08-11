package com.patricio.contreras.dto.response;

import com.patricio.contreras.domain.entity.User;
import lombok.Data;

@Data
public class FurgonResponseDTO {

	private Long id;
	
	private String patente;
	
	private String descripcion;

	private boolean enabled;

	private UserProfileResponseDTO usuarioTransportista;
}

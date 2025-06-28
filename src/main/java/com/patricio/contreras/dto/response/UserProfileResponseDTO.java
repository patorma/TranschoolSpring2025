package com.patricio.contreras.dto.response;

import com.patricio.contreras.domain.enums.Role;

import lombok.Data;

@Data
public class UserProfileResponseDTO {

	private Long id;
	
	private String nombres;
	
	private String apellidos;

	public String comuna;
	private String email;
	//se elimino el role  ff
	private Role role;


}

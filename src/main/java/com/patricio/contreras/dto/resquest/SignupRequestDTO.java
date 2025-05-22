package com.patricio.contreras.dto.resquest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
//registro de usuario lo que se va enviar
@Data
public class SignupRequestDTO {

	//@NotBlank indica que el atributo es obligatorio
		@NotBlank
		private String nombres;
		
		@NotBlank(message = "last name is mandatory")
		private String apellidos;
		
		@NotBlank(message = "Comuna is mandatory")
		private String comuna;
		
		@NotBlank(message = "Telefono is mandatory")
		private String telefono;
		
		@NotBlank(message = "Email is mandatory")
		@Email(message = "Invalid email")
		private String email;
		
		@NotNull(message = "Password is mandatory" )
		@Size(min = 4)
		private String password;
}

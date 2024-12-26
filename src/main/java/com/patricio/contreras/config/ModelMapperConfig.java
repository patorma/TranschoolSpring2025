package com.patricio.contreras.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	//cuando se quiere registrar un objeto que no pertenece al 
	//ecosistema  spring sino que se esta importando
	//ese objeto se registra dentro del contexto de spring
	//mediante la anotacion @Bean
	//y el @Bean le indica a un metodo que esta retornando el objeto ModelMapper
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();//retorna una instancia del objeto ModelMapper
	}
	
}

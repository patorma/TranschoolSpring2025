package com.patricio.contreras.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.TipoAlarma;
import com.patricio.contreras.dto.response.TipoAlarmaResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TipoAlarmaMapper {

	private final ModelMapper modelMapper;
	
	//toma la estructura de la entidad como dato de entrada
	//y mapearlo para obtener un objeto en este caso con la estructura de TipoAlarmaResponseDTO
	//trabaja de uno a uno de un objeto a un objeto resultante
	public TipoAlarmaResponseDTO toResponseDTO(TipoAlarma tipoAlarma) {
		return modelMapper.map(tipoAlarma, TipoAlarmaResponseDTO.class);
	}
	
	// a veces pueden enviar una lista de entidades para mapearlas a un alista de DTO
	// se recorre una lista transformandola a un flujo de datos mediante un stream
	//y por cada flujo u objeto que se recorre se hace la transformacion por medio del map
	
	public List<TipoAlarmaResponseDTO> toResponseDTOList(List<TipoAlarma> tipoAlarmas){
		return tipoAlarmas.stream()
				.map(this::toResponseDTO)//se llama al metodo toResponseDTO para transformar por objeto
				.toList();
	}
}

package com.patricio.contreras.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.Alarma;
import com.patricio.contreras.dto.response.AlarmaResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AlarmaMapper {

	//para hacer una inyeccion de depencias se necesita un constructor
	private final ModelMapper modelMapper;
	
	//toma la estructura de Alarma a la estructura del dto en este caso AlarmaResponseDTO
	public AlarmaResponseDTO toResponseDTO(Alarma alarma) {
		return modelMapper.map(alarma, AlarmaResponseDTO.class);
	}
	
	public List<AlarmaResponseDTO> toResponseDTOList(List<Alarma> alarmas){
		return alarmas.stream()
				.map(this::toResponseDTO)
				.toList();
	}
}

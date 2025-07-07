package com.patricio.contreras.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.AsignacionDeEstudiante;
import com.patricio.contreras.dto.response.AsignacionDeEstudianteResponseDTO;
import com.patricio.contreras.dto.resquest.AsignacionDeEstudianteRequestDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AsignacionDeEstudianteMapper {
	
	private final ModelMapper modelMapper;
	
	public AsignacionDeEstudiante toEntity(AsignacionDeEstudianteRequestDTO asignacionDeEstudianteRequestDTO) {
		return modelMapper.map(asignacionDeEstudianteRequestDTO, AsignacionDeEstudiante.class);
	}
	
	public AsignacionDeEstudianteResponseDTO toResponseDTO(AsignacionDeEstudiante asignacionDeEstudiante ) {
		return modelMapper.map(asignacionDeEstudiante,AsignacionDeEstudianteResponseDTO .class);
	}
	
	public List<AsignacionDeEstudianteResponseDTO>toResponseDTOList(List<AsignacionDeEstudiante> asignaciones){
		return asignaciones.stream()
					.map(this::toResponseDTO)
					.toList();
	}

}

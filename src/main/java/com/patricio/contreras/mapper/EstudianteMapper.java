package com.patricio.contreras.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.Estudiante;
import com.patricio.contreras.dto.response.EstudianteResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EstudianteMapper {
	
	private final ModelMapper modelMapper;
	
	public EstudianteResponseDTO toResponseDTO(Estudiante estudiante) {
		return modelMapper.map(estudiante, EstudianteResponseDTO.class);
	}
	
	public List<EstudianteResponseDTO> toResponseDTOList(List<Estudiante> estudiantes){
		return estudiantes.stream()
				.map(this::toResponseDTO)
				.toList();
	}

}

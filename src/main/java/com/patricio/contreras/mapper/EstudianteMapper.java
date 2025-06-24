package com.patricio.contreras.mapper;

import java.util.List;

import com.patricio.contreras.dto.resquest.EstudianteRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.Estudiante;
import com.patricio.contreras.dto.response.EstudianteResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EstudianteMapper {
	
	private final ModelMapper modelMapper;

	public Estudiante toEntity(EstudianteRequestDTO estudianteRequestDTO){
        return modelMapper.map(estudianteRequestDTO,Estudiante.class);
	}
	
	public EstudianteResponseDTO toResponseDTO(Estudiante estudiante) {
		return modelMapper.map(estudiante, EstudianteResponseDTO.class);
	}
	
	public List<EstudianteResponseDTO> toResponseDTOList(List<Estudiante> estudiantes){
		return estudiantes.stream()
				.map(this::toResponseDTO)
				.toList();
	}

}

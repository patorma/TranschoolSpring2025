package com.patricio.contreras.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.Estudiante;
import com.patricio.contreras.dto.response.EstudianteResponseDTO;
import com.patricio.contreras.mapper.EstudianteMapper;
import com.patricio.contreras.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EstudianteService {

	private final EstudianteRepository estudianteRepository;
	
	private final EstudianteMapper estudianteMapper;
	
	@Transactional(readOnly = true)
	public List<EstudianteResponseDTO> getAllEstudiantes(){
		
		List<Estudiante> estudiantes = estudianteRepository.findAll();
		
		return estudianteMapper.toResponseDTOList(estudiantes);
	}
}

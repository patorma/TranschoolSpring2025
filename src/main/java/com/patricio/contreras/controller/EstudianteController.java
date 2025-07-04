package com.patricio.contreras.controller;

import java.util.List;

import com.patricio.contreras.dto.resquest.EstudianteRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.patricio.contreras.dto.response.EstudianteResponseDTO;
import com.patricio.contreras.service.EstudianteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	private final EstudianteService estudianteService;
	
	/*@GetMapping
	public ResponseEntity<List<EstudianteResponseDTO>> getAllEstudiantes(){
		List<EstudianteResponseDTO> estudiantes = estudianteService.getAllEstudiantes();
		return ResponseEntity.ok(estudiantes);
	}*/

	@PostMapping("/estudiante")
	public ResponseEntity<EstudianteResponseDTO> createEstudiante(
			@RequestBody @Validated EstudianteRequestDTO
			estudianteRequestDTO
			){
		EstudianteResponseDTO estudianteResponseDTO = estudianteService.createEstudiante(estudianteRequestDTO);
		return new ResponseEntity<>(estudianteResponseDTO, HttpStatus.CREATED);
	}
}

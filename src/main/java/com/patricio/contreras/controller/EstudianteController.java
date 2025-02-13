package com.patricio.contreras.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.dto.response.EstudianteResponseDTO;
import com.patricio.contreras.service.EstudianteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	private final EstudianteService estudianteService;
	
	@GetMapping
	public ResponseEntity<List<EstudianteResponseDTO>> getAllEstudiantes(){
		List<EstudianteResponseDTO> estudiantes = estudianteService.getAllEstudiantes();
		return ResponseEntity.ok(estudiantes);
	}
}

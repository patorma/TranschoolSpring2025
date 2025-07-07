package com.patricio.contreras.controller;

import java.util.List;

import com.patricio.contreras.dto.resquest.EstudianteRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	

	@PostMapping("/estudiante")
	public ResponseEntity<EstudianteResponseDTO> createEstudiante(
			@RequestBody @Validated EstudianteRequestDTO
			estudianteRequestDTO
			){
		EstudianteResponseDTO estudianteResponseDTO = estudianteService.createEstudiante(estudianteRequestDTO);
		return new ResponseEntity<>(estudianteResponseDTO, HttpStatus.CREATED);
	}
	@PreAuthorize("hasRole('APODERADO')")
	@GetMapping("/apoderado/estudiantes")
	public ResponseEntity<List<EstudianteResponseDTO>> getMyEstudiantes(){
		List<EstudianteResponseDTO> estudianteResponseDTO = estudianteService.apoderadoEstudiantes();
		return ResponseEntity.ok(estudianteResponseDTO);
	}

	/*	@PreAuthorize("hasRole('TRANSPORTISTA')")
	@GetMapping("my-furgon")
	public ResponseEntity<FurgonResponseDTO> getFurgon(){
          FurgonResponseDTO furgon = furgonService.getMyFurgon();
		  return ResponseEntity.ok(furgon);
	}*/
}

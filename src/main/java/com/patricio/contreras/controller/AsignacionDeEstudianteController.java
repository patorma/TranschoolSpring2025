package com.patricio.contreras.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.dto.response.AsignacionDeEstudianteResponseDTO;
import com.patricio.contreras.dto.resquest.AsignacionDeEstudianteRequestDTO;
import com.patricio.contreras.service.AsignacionDeEstudianteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/asignaciones")
public class AsignacionDeEstudianteController {
	
	private final AsignacionDeEstudianteService asignacionDeEstudianteService;
	

	@PostMapping("/asignacion")
	public ResponseEntity<AsignacionDeEstudianteResponseDTO> createAsignacion(
			@RequestBody @Validated AsignacionDeEstudianteRequestDTO 
			asignacionDeEstudianteRequestDTO ){
		AsignacionDeEstudianteResponseDTO asignacionDeEstudianteResponseDTO=
				asignacionDeEstudianteService.createAsignaciones(asignacionDeEstudianteRequestDTO);
		return new ResponseEntity<>(asignacionDeEstudianteResponseDTO,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<AsignacionDeEstudianteResponseDTO>>getAllAsignaciones(
			@PageableDefault(size = 5)Pageable pageable){
		Page<AsignacionDeEstudianteResponseDTO>asignaciones =
				asignacionDeEstudianteService.getAllAsignaciones(pageable);
		return ResponseEntity.ok(asignaciones);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AsignacionDeEstudianteResponseDTO>getAsignacionById(
			@PathVariable Long id) {
		AsignacionDeEstudianteResponseDTO asignacion = 
				asignacionDeEstudianteService.getAsignacionById(id);
		return ResponseEntity.ok(asignacion);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> deleteAsignacion(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		asignacionDeEstudianteService.deleteAsignacion(id);
		 response.put("mensaje", "La asignación fue eliminada con éxito!");
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

	@GetMapping("/page/idEstudiante")
	public ResponseEntity<Page<AsignacionDeEstudianteResponseDTO>>findByIdEstudiante(
			@RequestParam Long id,@PageableDefault(size = 5) Pageable pageable){
		
		Page<AsignacionDeEstudianteResponseDTO> asignaciones = 
				asignacionDeEstudianteService.getAsignacionByEstudianteId(id, pageable);
		return ResponseEntity.ok(asignaciones);
	}
	
	@GetMapping("/page/idFurgon")
	public ResponseEntity<Page<AsignacionDeEstudianteResponseDTO>>findByFurgon(
			@RequestParam Long id,@PageableDefault(size = 5) Pageable pageable	){
		Page<AsignacionDeEstudianteResponseDTO> asignaciones = 
				asignacionDeEstudianteService.getAsignacionByFurgonId(id, pageable);
		return ResponseEntity.ok(asignaciones);
	}
}

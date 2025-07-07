package com.patricio.contreras.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.dto.response.RecorridoResponseDTO;
import com.patricio.contreras.service.RecorridoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recorridos")
public class RecorridoController {

	private final RecorridoService recorridoService;
	
	@GetMapping("/page")
	public ResponseEntity<Page<RecorridoResponseDTO>> getAllRecorridos(
			@PageableDefault(size = 5)Pageable pageable){
		Page<RecorridoResponseDTO> recorridos = recorridoService.getAllRecorridos(pageable);
		return ResponseEntity.ok(recorridos);
	} 
	
	@GetMapping("/page/id-estudiante")
	public ResponseEntity<Page<RecorridoResponseDTO>> findByIdEstudiante(@RequestParam Long id,@PageableDefault(size = 5) Pageable pageable){
		
		Page<RecorridoResponseDTO> recorridos = recorridoService.getRecorridoByEstudianteId(id, pageable);
		return ResponseEntity.ok(recorridos);
	}
	

	
	@GetMapping("/{id}")
	public ResponseEntity<RecorridoResponseDTO> getRecorridoById(@PathVariable Long id){
		RecorridoResponseDTO recorrido = recorridoService.getRecorridoById(id);
		return ResponseEntity.ok(recorrido);
	}
}

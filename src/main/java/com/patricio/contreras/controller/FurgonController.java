package com.patricio.contreras.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.dto.response.FurgonResponseDTO;
import com.patricio.contreras.dto.resquest.FurgonRequestDTO;
import com.patricio.contreras.service.FurgonService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/furgones")
public class FurgonController {

	private final FurgonService furgonService;

/*	@GetMapping
	public ResponseEntity<List<FurgonResponseDTO>> getAllFurgones(){
		List<FurgonResponseDTO> furgones = furgonService.getAllFurgones();
		return ResponseEntity.ok(furgones);
	}*/
	
	@PostMapping("/input")
	public ResponseEntity<FurgonResponseDTO> createFurgon(
			@RequestBody @Validated FurgonRequestDTO furgonRequestDTO
			){
		FurgonResponseDTO furgonResponseDTO = furgonService
				.CreateFurgon(furgonRequestDTO);
		return new ResponseEntity<>(furgonResponseDTO,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/elimina/{id}")
	public ResponseEntity<?> deleteFurgon(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		 furgonService.deleteFurgon(id);
		 response.put("mensaje", "El furgon fue eliminado con éxito!");
		 return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}

}

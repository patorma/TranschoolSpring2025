package com.patricio.contreras.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


	@PreAuthorize("hasRole('TRANSPORTISTA')")
	@GetMapping("my-furgon")
	public ResponseEntity<FurgonResponseDTO> getFurgon(){
          FurgonResponseDTO furgon = furgonService.getMyFurgon();
		  return ResponseEntity.ok(furgon);
	}

}

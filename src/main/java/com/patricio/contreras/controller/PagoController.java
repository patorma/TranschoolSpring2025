package com.patricio.contreras.controller;

import com.patricio.contreras.dto.resquest.PagoRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.patricio.contreras.domain.enums.Estado;
import com.patricio.contreras.dto.response.PagoResponseDTO;
import com.patricio.contreras.service.PagoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagos")
public class PagoController {

	private final PagoService pagoService;


	
/*	@GetMapping("/page/id-user")
	public ResponseEntity<Page<PagoResponseDTO>>findByIdUser(@RequestParam Long id,@PageableDefault(size = 5) Pageable pageable){
		Page<PagoResponseDTO> pagos = pagoService.getPagoByUserId(id, pageable);
		return ResponseEntity.ok(pagos);
	}*/
	
	/*@GetMapping("/{id}")
	public ResponseEntity<PagoResponseDTO> getPagoById(@PathVariable Long id){
		PagoResponseDTO pago = pagoService.getPagoById(id);
		return ResponseEntity.ok(pago);
	}*/





}

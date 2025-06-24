package com.patricio.contreras.controller;

import com.patricio.contreras.dto.resquest.PagoRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/page")
	public ResponseEntity<Page<PagoResponseDTO>> getAllPagos(
			@PageableDefault(size = 5)Pageable pageable){
		Page<PagoResponseDTO> pagos = pagoService.getAllPagos(pageable);
		return ResponseEntity.ok(pagos);
	}
	
	@GetMapping("/page/state")
	public ResponseEntity<Page<PagoResponseDTO>> findByyState(
			@RequestParam Estado state_pago,@PageableDefault(sort = "estado",size = 5) Pageable pageable){
		
		Page<PagoResponseDTO> pagos = pagoService.getPagoByState(state_pago, pageable);
		return ResponseEntity.ok(pagos);	
	}
	
	@GetMapping("/page/id-user")
	public ResponseEntity<Page<PagoResponseDTO>>findByIdUser(@RequestParam Long id,@PageableDefault(size = 5) Pageable pageable){
		Page<PagoResponseDTO> pagos = pagoService.getPagoByUserId(id, pageable);
		return ResponseEntity.ok(pagos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PagoResponseDTO> getPagoById(@PathVariable Long id){
		PagoResponseDTO pago = pagoService.getPagoById(id);
		return ResponseEntity.ok(pago);
	}

	@PostMapping("pago")
    public ResponseEntity<PagoResponseDTO> createPago(
			@RequestBody @Validated PagoRequestDTO pagoRequestDTO
			){
		PagoResponseDTO pagoResponseDTO = pagoService.createPago(pagoRequestDTO);
		return new ResponseEntity<>(pagoResponseDTO, HttpStatus.CREATED);
	}


}

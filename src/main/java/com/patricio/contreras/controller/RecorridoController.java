package com.patricio.contreras.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patricio.contreras.dto.response.RecorridoResponseDTO;
import com.patricio.contreras.service.RecorridoService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recorridos")
public class RecorridoController {

	private final RecorridoService recorridoService;

   // El transportista ve los recorridos que tiene asignados
    @PreAuthorize("hasRole('TRANSPORTISTA')")
    @GetMapping("/my-recorridos")
    public  ResponseEntity<List<RecorridoResponseDTO>> getMyRecorridos(){
        List<RecorridoResponseDTO> recorridos = recorridoService.getRecorridosTransportistas();
        return ResponseEntity.ok(recorridos);
    }
	

	

}

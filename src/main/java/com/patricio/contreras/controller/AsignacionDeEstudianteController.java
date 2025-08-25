package com.patricio.contreras.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.patricio.contreras.dto.response.EstudianteResponseDTO;
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

    // El trasportista ve los estudiantes que tiene asignado
    @PreAuthorize("hasRole('TRANSPORTISTA')")
    @GetMapping("/my-estudiantes")
    public  ResponseEntity<List<EstudianteResponseDTO>> getMyEstudiantes(){
        List<EstudianteResponseDTO> estudiantes = asignacionDeEstudianteService.estudiantesByTransportist();
        return ResponseEntity.ok(estudiantes);
    }
	

	


}

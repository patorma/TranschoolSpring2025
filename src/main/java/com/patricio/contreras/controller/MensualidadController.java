package com.patricio.contreras.controller;

import com.patricio.contreras.dto.response.MensualidadResponseDTO;
import com.patricio.contreras.service.MensualidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensualidades")
public class MensualidadController {

    private final MensualidadService mensualidadService;

    @PreAuthorize("hasRole('APODERADO')")
    @GetMapping("my-mensualidades")
    public ResponseEntity<List<MensualidadResponseDTO>> getMyMemnsualidad(){
       List<MensualidadResponseDTO> mensualidades =mensualidadService.getMyMensualidades();
       return ResponseEntity.ok(mensualidades);
    }
}

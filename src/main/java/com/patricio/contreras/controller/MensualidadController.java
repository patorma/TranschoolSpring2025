package com.patricio.contreras.controller;

import com.patricio.contreras.dto.response.MensualidadResponseDTO;
import com.patricio.contreras.service.MensualidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @GetMapping("my-mensualidades/page")
    public ResponseEntity<Page<MensualidadResponseDTO>> getMyMemnsualidad(
            @PageableDefault(size = 5) Pageable pageable
    ){
       Page<MensualidadResponseDTO> mensualidades =mensualidadService.getMyMensualidades(pageable);
       return ResponseEntity.ok(mensualidades);
    }
}

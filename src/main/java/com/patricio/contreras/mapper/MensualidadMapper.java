package com.patricio.contreras.mapper;

import com.patricio.contreras.domain.entity.Mensualidad;
import com.patricio.contreras.dto.response.MensualidadResponseDTO;

import com.patricio.contreras.dto.resquest.MensualidadRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MensualidadMapper {

    private final ModelMapper modelMapper;

    public MensualidadResponseDTO toResponseDTO(Mensualidad mensualidad){
        return modelMapper.map(mensualidad, MensualidadResponseDTO.class);
    }

    public List<MensualidadResponseDTO> toResponseDTOList(List<Mensualidad> mensualidades){
        return mensualidades.stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Mensualidad toEntity(MensualidadRequestDTO mensualidadRequestDTO){
        return modelMapper.map(mensualidadRequestDTO,Mensualidad.class);
    }
}

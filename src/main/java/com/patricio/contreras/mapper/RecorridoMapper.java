package com.patricio.contreras.mapper;

import java.util.List;

import com.patricio.contreras.dto.resquest.RecorridoRequestDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.Recorrido;
import com.patricio.contreras.dto.response.RecorridoResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RecorridoMapper {

	private final ModelMapper modelMapper;
	
	public RecorridoResponseDTO toResponseDTO(Recorrido recorrido) {
		return  modelMapper.map(recorrido, RecorridoResponseDTO.class);
	}
	
	public List<RecorridoResponseDTO> toResponseDTOList(List<Recorrido> recorridos){
		return recorridos.stream()
				.map(this::toResponseDTO)
				.toList();
		
	}

	public Recorrido toEntity(RecorridoRequestDTO recorridoRequestDTO){
		return modelMapper.map(recorridoRequestDTO,Recorrido.class);
	}
}

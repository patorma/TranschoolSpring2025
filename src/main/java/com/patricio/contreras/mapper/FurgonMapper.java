package com.patricio.contreras.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.Furgon;
import com.patricio.contreras.dto.response.FurgonResponseDTO;
import com.patricio.contreras.dto.resquest.FurgonRequestDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FurgonMapper {

	private final ModelMapper modelMapper;
	
	public Furgon toEntity(FurgonRequestDTO furgonRequestDTO) {
		return modelMapper.map(furgonRequestDTO, Furgon.class);
	}
	
	public FurgonResponseDTO toResponseDTO(Furgon furgon) {
		return modelMapper.map(furgon, FurgonResponseDTO.class);
	}
	
	public List<FurgonResponseDTO> toResponseDTOList(List<Furgon> furgones){
		return furgones.stream()
					.map(this::toResponseDTO)
					.toList();
	}
	

}

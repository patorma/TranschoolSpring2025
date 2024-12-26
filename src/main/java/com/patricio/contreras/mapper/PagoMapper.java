package com.patricio.contreras.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.patricio.contreras.domain.entity.Pago;
import com.patricio.contreras.dto.response.PagoResponseDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PagoMapper {

	private final ModelMapper modelMapper;
	
	public PagoResponseDTO toResponseDTO(Pago pago) {
		return modelMapper.map(pago, PagoResponseDTO.class);
	}
	
	public List<PagoResponseDTO> toResponseDTOList(List<Pago> pagos){
		return pagos.stream()
				.map(this::toResponseDTO)
				.toList();
				
		
	}
}

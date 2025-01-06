package com.patricio.contreras.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.Furgon;
import com.patricio.contreras.dto.response.FurgonResponseDTO;
import com.patricio.contreras.mapper.FurgonMapper;
import com.patricio.contreras.repository.FurgonRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class FurgonService {
	
	private final FurgonRepository  furgonRepository;
	
	private final FurgonMapper furgonMapper;
	
	@Transactional(readOnly = true)
	public List<FurgonResponseDTO> getAllFurgones(){
		List<Furgon> furgones = furgonRepository.findAll();
		
		return furgonMapper.toResponseDTOList(furgones);
	} 

}

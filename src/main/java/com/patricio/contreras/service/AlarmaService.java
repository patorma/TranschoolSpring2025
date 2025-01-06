package com.patricio.contreras.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.patricio.contreras.domain.entity.Alarma;
import com.patricio.contreras.dto.response.AlarmaResponseDTO;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.AlarmaMapper;
import com.patricio.contreras.repository.AlarmaRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlarmaService {

	//se hace inyeccion de dependencias 
	private final AlarmaRepository alarmaRepository;
	
	private final AlarmaMapper alarmaMapper;
	
	
	/*@Transactional(readOnly= true)
	public List<AlarmaResponseDTO> getAllAlarmas(){
		List<Alarma> alarmas = alarmaRepository.findAll();
		return alarmaMapper.toResponseDTOList(alarmas);
	}*/
	
	@Transactional(readOnly = true)
	public Page<AlarmaResponseDTO> getAllAlarmas(Pageable pageable){
		Page<Alarma> alarmas = alarmaRepository.findAll(pageable);
		return alarmas.map(alarmaMapper::toResponseDTO);
	}
	
	@Transactional(readOnly = true)
	public Page<AlarmaResponseDTO> 	getAlarmasBytipoAlarmaName(String tipoAlarmaName, Pageable pageable ){
		Page<Alarma> alarmas = alarmaRepository.findByTipoAlarmaName(tipoAlarmaName, pageable);
		return alarmas.map(alarmaMapper::toResponseDTO);
	}
	
	public AlarmaResponseDTO getAlarmaById(Long id) {
		Alarma alarma = alarmaRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Alarma not found with id: " + id));
		
		return alarmaMapper.toResponseDTO(alarma);
	}
	
	@Transactional(readOnly = true)
	public Page<AlarmaResponseDTO> getAlarmaByUserId(Long id,Pageable pageable) {
		Page<Alarma> alarmasByIdUser  = alarmaRepository.findByUserId(id, pageable);
		return alarmasByIdUser.map(alarmaMapper::toResponseDTO);
		
	}
	
	
	
	
	
}

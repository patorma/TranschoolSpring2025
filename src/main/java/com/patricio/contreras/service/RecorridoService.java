package com.patricio.contreras.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.Recorrido;
import com.patricio.contreras.dto.response.RecorridoResponseDTO;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.RecorridoMapper;
import com.patricio.contreras.repository.RecorridoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecorridoService {

	private final RecorridoRepository recorridoRepository;
	
	private final RecorridoMapper recorridoMapper;
	
	@Transactional(readOnly = true)
	public Page<RecorridoResponseDTO> getAllRecorridos(Pageable pageable){
		Page<Recorrido> recorridos = recorridoRepository.findAll(pageable);
		return recorridos.map(recorridoMapper::toResponseDTO);
	}
	
   public RecorridoResponseDTO getRecorridoById(Long id) {
	   Recorrido recorrido = recorridoRepository.findById(id)
			      .orElseThrow(()-> new ResourceNotFoundException("Recorrido not found with id: " + id));
	   return recorridoMapper.toResponseDTO(recorrido);
   }
   
   @Transactional(readOnly = true)
   public Page<RecorridoResponseDTO> getRecorridoByEstudianteId(Long id,Pageable pageable){
	   Page<Recorrido>  recorridosByIdEstudiante = recorridoRepository.findByEstudianteId(id, pageable);
	   return  recorridosByIdEstudiante .map(recorridoMapper::toResponseDTO);
	   
   }
   
   @Transactional(readOnly = true)
   public Page<RecorridoResponseDTO> getRecorridoByIdFurgon(Long id, Pageable pageable){
	   Page<Recorrido>  recorridosByIdFurgon = recorridoRepository.findByFurgonId(id, pageable);
	   return recorridosByIdFurgon.map(recorridoMapper::toResponseDTO);
   }
   
   
   
	
	 
}

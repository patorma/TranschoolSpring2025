package com.patricio.contreras.service;

import com.patricio.contreras.domain.entity.Estudiante;
import com.patricio.contreras.dto.resquest.RecorridoRequestDTO;
import com.patricio.contreras.dto.resquest.UpdateRecorridoRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.repository.EstudianteRepository;
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

	private final EstudianteRepository estudianteRepository;
	
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
   
  /* @Transactional(readOnly = true)
   public Page<RecorridoResponseDTO> getRecorridoByEstudianteId(Long id,Pageable pageable){
	   Page<Recorrido>  recorridosByIdEstudiante = recorridoRepository.findByEstudianteId(id, pageable);
	   return  recorridosByIdEstudiante .map(recorridoMapper::toResponseDTO);
	   
   }*/

   @Transactional
   public RecorridoResponseDTO createRecorrido(RecorridoRequestDTO recorridoRequestDTO){
	   boolean origenAlreadyExists =recorridoRepository.existsByOrigen(recorridoRequestDTO.getOrigen());

	   if (origenAlreadyExists) {
		   throw new BadRequestException("El origen ya está registrado en el sistema!!!.");
	   }




	   Recorrido recorrido = recorridoMapper.toEntity(recorridoRequestDTO);
	  // recorrido.setEstudiante(estudiante);
	   recorridoRepository.save(recorrido);
	   return recorridoMapper.toResponseDTO(recorrido);
   }

   @Transactional
   public RecorridoResponseDTO updateReciorrido(Long id, UpdateRecorridoRequestDTO updateRecorridoRequestDTO){

		Recorrido recorridoActual = recorridoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Recorrido no encontrado!!!"));

		Recorrido recorridoUpdated;

		if(updateRecorridoRequestDTO.getOrigen() != null) recorridoActual.setOrigen(updateRecorridoRequestDTO.getOrigen());
		if(updateRecorridoRequestDTO.getDestino() != null) recorridoActual.setDestino(updateRecorridoRequestDTO.getDestino());
		if(updateRecorridoRequestDTO.getDescripcion() != null) recorridoActual.setDescripcion(updateRecorridoRequestDTO.getDescripcion());

		recorridoUpdated = recorridoRepository.save(recorridoActual);

		return recorridoMapper.toResponseDTO(recorridoUpdated);
   }



 
   
   
   
	
	 
}

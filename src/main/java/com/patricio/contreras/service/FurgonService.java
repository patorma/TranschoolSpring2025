package com.patricio.contreras.service;


import java.util.List;


import com.patricio.contreras.domain.entity.User;
import com.patricio.contreras.dto.resquest.UpdateFurgonRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.Furgon;
import com.patricio.contreras.dto.response.FurgonResponseDTO;
import com.patricio.contreras.dto.resquest.FurgonRequestDTO;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.FurgonMapper;
import com.patricio.contreras.repository.FurgonRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class FurgonService {
	
	private final FurgonRepository  furgonRepository;
	
	private final FurgonMapper furgonMapper;

	private final UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<FurgonResponseDTO> getAllFurgones(){
		List<Furgon> furgones = furgonRepository.findAllEnabled();
		
		return furgonMapper.toResponseDTOList(furgones);
	}

	@Transactional(readOnly = true)
	public FurgonResponseDTO getMyFurgon(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		User user = userRepository.findOneByEmail(email)
				.orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));

		Furgon furgon = furgonRepository.findByUsuarioTransportista_Id(user.getId())
				.orElseThrow(()-> new ResourceNotFoundException("No tienes un furgon asignado"));
		return furgonMapper.toResponseDTO(furgon);
	}

	@Transactional
	public FurgonResponseDTO CreateFurgon(FurgonRequestDTO furgonRequestDTO) {
    //quede acá
       boolean patenteAlreadyExists = furgonRepository.existsByPatente(furgonRequestDTO.getPatente());

	   if(patenteAlreadyExists){
		   throw new BadRequestException("La patente ya está registrada!!!!!!.");
	   }

	   //Cargar usuario transportista desde la bd
		User user =userRepository.findById(furgonRequestDTO.getUsuarioTransportistaId())
				.orElseThrow(()->new ResourceNotFoundException("Usuario Transportista no encontrado!!"));



	   Furgon furgon = furgonMapper.toEntity(furgonRequestDTO);
	   furgon.setUsuarioTransportista(user);

	   furgonRepository.save(furgon);
	   return furgonMapper.toResponseDTO(furgon);
	}

	@Transactional
	public FurgonResponseDTO updateFurgon(Long id, UpdateFurgonRequestDTO updateFurgonRequestDTO){
       //se obtiene el furgon a modificar
        Furgon furgonActual = furgonRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Furgon no encontrado!!") );

		Furgon furgonUpdated;


		/*boolean patenteAlreadyExists = furgonRepository.existsByPatente(updateFurgonRequestDTO.getPatente());
		if(patenteAlreadyExists){
			throw new BadRequestException(" Esa patente esta en el sistema!!!!!!.");
		}*/

		if(updateFurgonRequestDTO.getPatente() != null) furgonActual.setPatente(updateFurgonRequestDTO.getPatente());
		if(updateFurgonRequestDTO.getDescripcion() != null) furgonActual.setDescripcion(updateFurgonRequestDTO.getDescripcion());

		furgonUpdated= furgonRepository.save(furgonActual);

		return furgonMapper.toResponseDTO(furgonUpdated);
	}
	
	@Transactional
	public  void deleteFurgon(Long id) {
		
		
		
	Furgon furgon = furgonRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Furgon not found with id:" + id));

	furgon.setEnabled(false);
		furgonRepository.save(furgon);
	}

	@Transactional
	public void reactivarFurgon(Long id){
		Furgon furgon = furgonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Furgon not found with id: " + id));

		if(furgon.isEnabled()){
			throw new BadRequestException("El furgón con id " +"  "+ id + " ya está activado.");
		}
		furgon.setEnabled(true);
		furgonRepository.save(furgon);
	}
}

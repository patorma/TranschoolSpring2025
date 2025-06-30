package com.patricio.contreras.service;

import java.util.List;

import com.patricio.contreras.domain.entity.User;
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
		List<Furgon> furgones = furgonRepository.findAll();
		
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
		Furgon furgon = furgonMapper.toEntity(furgonRequestDTO);
		furgon.setPatente(furgon.getPatente());
		furgon.setDescripcion(furgon.getDescripcion());
		
		furgon = furgonRepository.save(furgon);
		
		return furgonMapper.toResponseDTO(furgon);
	}
	
	@Transactional
	public  void deleteFurgon(Long id) {
		
		
		
		furgonRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Furgon not found with id:" + id));
		
		furgonRepository.deleteById(id);
	}
}

package com.patricio.contreras.service;

import java.util.List;

import com.patricio.contreras.domain.entity.Recorrido;
import com.patricio.contreras.domain.entity.User;
import com.patricio.contreras.domain.enums.Role;
import com.patricio.contreras.dto.resquest.EstudianteRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.repository.RecorridoRepository;
import com.patricio.contreras.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.Estudiante;
import com.patricio.contreras.dto.response.EstudianteResponseDTO;
import com.patricio.contreras.mapper.EstudianteMapper;
import com.patricio.contreras.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EstudianteService {

	private final EstudianteRepository estudianteRepository;

	private final UserRepository userRepository;

	private final RecorridoRepository recorridoRepository;
	
	private final EstudianteMapper estudianteMapper;
	
	@Transactional(readOnly = true)
	public List<EstudianteResponseDTO> getAllEstudiantes(){
		
		List<Estudiante> estudiantes  = estudianteRepository.findAll();
		
		return estudianteMapper.toResponseDTOList(estudiantes);
	}
	@Transactional
	public EstudianteResponseDTO createEstudiante(EstudianteRequestDTO estudianteRequestDTO){


		// 1. Obtener email del usuario autenticado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User usuario = userRepository.findOneByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario autenticado no encontrado"));
		// 3. Validar que sea un apoderado
		if (!usuario.getRole().equals(Role.APODERADO)) {
			throw new AccessDeniedException("Solo usuarios con rol APODERADO pueden crear estudiantes");
		}
		Estudiante estudiante = estudianteMapper.toEntity(estudianteRequestDTO);

		/*User usuario = userRepository.findById(estudianteRequestDTO.getUsuarioId())
						.orElseThrow(()-> new ResourceNotFoundException("user not found by id"));*/

		/*Recorrido recorrido = recorridoRepository.findById(estudianteRequestDTO.getRecorridoId())
						.orElseThrow(()->new ResourceNotFoundException("recorrido not found by id"));*/

		estudiante.setNombres(estudiante.getNombres());
		estudiante.setApellidos(estudiante.getApellidos());
		estudiante.setFechaNacimiento(estudiante.getFechaNacimiento());
		estudiante.setColegio(estudiante.getColegio());
		estudiante.setUsuario(usuario);
		//estudiante.setRecorrido(recorrido);

		boolean idAlreadyExists = estudianteRepository.existsByEmail(estudianteRequestDTO.getEmail());
		if(idAlreadyExists){
			throw new BadRequestException("El estudiante ya fue ingresado!!!!!.");
		}



		estudiante = estudianteRepository.save(estudiante);

		return estudianteMapper.toResponseDTO(estudiante);

	}
	@Transactional
	public void deleteEstudiante(Long id){
		estudianteRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Estudiante not found with id:" + id));
		estudianteRepository.deleteById(id);
	}
}

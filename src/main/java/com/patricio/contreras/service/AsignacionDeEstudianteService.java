package com.patricio.contreras.service;

import java.util.List;

import com.patricio.contreras.domain.entity.*;
import com.patricio.contreras.dto.response.EstudianteResponseDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.mapper.EstudianteMapper;
import com.patricio.contreras.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.dto.response.AsignacionDeEstudianteResponseDTO;
import com.patricio.contreras.dto.resquest.AsignacionDeEstudianteRequestDTO;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.AsignacionDeEstudianteMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AsignacionDeEstudianteService {
	
	private final AsignacionDeEstudianteRepository asignacionDeEstudianteRepository;
    private final UserRepository userRepository;
	private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
	private final FurgonRepository furgonRepository;

	private final RecorridoRepository recorridoRepository;
	
	private final AsignacionDeEstudianteMapper asignacionDeEstudianteMapper;
	
	@Transactional(readOnly = true)
	public Page<AsignacionDeEstudianteResponseDTO> getAllAsignaciones(Pageable pageable){
		
		Page<AsignacionDeEstudiante> asignaciones = asignacionDeEstudianteRepository.findAll(pageable);
		return asignaciones.map(asignacionDeEstudianteMapper::toResponseDTO);
	}
	@Transactional
	public AsignacionDeEstudianteResponseDTO createAsignaciones(AsignacionDeEstudianteRequestDTO asignacionDeEstudianteRequestDTO ) {
        boolean idEstudianteExists = asignacionDeEstudianteRepository.existsByEstudiante_Id(asignacionDeEstudianteRequestDTO.getEstudianteId());

		//boolean idFurgonExists = asignacionDeEstudianteRepository.existsByFurgon_Id(asignacionDeEstudianteRequestDTO.getFurgonId());

		// esto es para evitar ingresar el mismo estudiante con los mismos datos
		// neuvamente
		if(idEstudianteExists){
			throw new BadRequestException("Ese estudiante esta registrado!!!!!!.");
		}

		Estudiante estudiante = estudianteRepository.findById(asignacionDeEstudianteRequestDTO.getEstudianteId())
				                         .orElseThrow(()-> new ResourceNotFoundException("student not found by id"));
		
		Furgon furgon = furgonRepository.findById(asignacionDeEstudianteRequestDTO.getFurgonId())
										.orElseThrow(()-> new ResourceNotFoundException("furgon not found by id"));

		Recorrido recorrido = recorridoRepository.findById(asignacionDeEstudianteRequestDTO.getRecorridoId())
				.orElseThrow(()-> new ResourceNotFoundException("recorrido not found by id"));

		AsignacionDeEstudiante asignacion =
				asignacionDeEstudianteMapper.toEntity(asignacionDeEstudianteRequestDTO);
		
		asignacion.setFechaRegistro(asignacion.getFechaRegistro());
		asignacion.setEstudiante(estudiante );
		asignacion.setFurgon(furgon);
		asignacion.setRecorrido(recorrido);
		
		asignacion = asignacionDeEstudianteRepository.save(asignacion);
		
		return asignacionDeEstudianteMapper.toResponseDTO(asignacion);
	}
	

	public AsignacionDeEstudianteResponseDTO getAsignacionById(Long id) {
		AsignacionDeEstudiante asignacion = 
				asignacionDeEstudianteRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Asignacion not found with id:" + id));
		return asignacionDeEstudianteMapper.toResponseDTO(asignacion);
		
	}
	
	@Transactional(readOnly = true)
	public AsignacionDeEstudianteResponseDTO getAsignacionByEstudianteId(Long id){
		
		AsignacionDeEstudiante asignacionEstudianteByIdEstudiante =
				asignacionDeEstudianteRepository.findByEstudianteId(id).
						orElseThrow(()->new ResourceNotFoundException("El estudiante no tiene asignaciones"));
		return asignacionDeEstudianteMapper.toResponseDTO(asignacionEstudianteByIdEstudiante);
		
	}

	@Transactional(readOnly = true)
	public  Page<AsignacionDeEstudianteResponseDTO> getAsignacionByFurgonId(Long id,Pageable pageable){
		Page<AsignacionDeEstudiante> asignacionByIdFurgon = 
				asignacionDeEstudianteRepository.findByFurgon(id,pageable);
		return asignacionByIdFurgon.map(asignacionDeEstudianteMapper::toResponseDTO);
	}
	
	@Transactional
	public void deleteAsignacion(Long id) {
		asignacionDeEstudianteRepository.findById(id)
		  .orElseThrow(()-> new ResourceNotFoundException("Asignacion not found with id:" + id));
	
		asignacionDeEstudianteRepository.deleteById(id);
		
		//quede acá mver controller
		
	}


	@Transactional
	public Integer contarAsignacionesPorFurgon(Long furgonId) {
		return asignacionDeEstudianteRepository.contarEstudiantesAsignados(furgonId);
	}

    @Transactional(readOnly = true)
    public List<EstudianteResponseDTO> estudiantesByTransportist(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findOneByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));
        List<Estudiante> estudiantes = asignacionDeEstudianteRepository.obtenerEstudiantesTransportista(user.getId());
        return estudianteMapper.toResponseDTOList(estudiantes);
    }

}

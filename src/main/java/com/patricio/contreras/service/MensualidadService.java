package com.patricio.contreras.service;

import com.patricio.contreras.domain.entity.Mensualidad;
import com.patricio.contreras.domain.entity.User;
import com.patricio.contreras.domain.enums.Estado;
import com.patricio.contreras.dto.response.MensualidadResponseDTO;
import com.patricio.contreras.dto.resquest.MensualidadRequestDTO;
import com.patricio.contreras.dto.resquest.UpdateMensualidadRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.MensualidadMapper;
import com.patricio.contreras.repository.MensualidadRepository;
import com.patricio.contreras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MensualidadService {

    private final MensualidadRepository mensualidadRepository;
    private final UserRepository userRepository;

    private final  MensualidadMapper mensualidadMapper;

    @Transactional(readOnly = true)
   public Page<MensualidadResponseDTO> getAllMensualidades(Pageable pageable){
       Page<Mensualidad> mensualidades = mensualidadRepository.findAllEnabled(pageable);

       return mensualidades.map(mensualidadMapper::toResponseDTO);
   }

   @Transactional(readOnly = true)
   public Page<MensualidadResponseDTO> getMensualidadByEstado(Estado estado,Pageable pageable){
       Page<Mensualidad> mensualidads = mensualidadRepository.findByEstado(estado,pageable);

       return mensualidads.map(mensualidadMapper::toResponseDTO);
    }

   @Transactional(readOnly = true)
   public MensualidadResponseDTO getMensualidadById(Long id){
      Mensualidad mensualidad = mensualidadRepository.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Mensualidad not found with id:" + id));
      return mensualidadMapper.toResponseDTO(mensualidad);
   }

   @Transactional(readOnly = true)
   public Page<MensualidadResponseDTO> getMensualidadByUserId(Long id, Pageable pageable){
        Page<Mensualidad> mensualidadesByIdUser = mensualidadRepository.findByUsuarioId(id,pageable);
        return mensualidadesByIdUser.map(mensualidadMapper::toResponseDTO);
   }

   @Transactional
   public MensualidadResponseDTO createMensualidad(MensualidadRequestDTO mensualidadRequestDTO){

       //Cargar usuario apoderado desde la bd
       User user = userRepository.findById(mensualidadRequestDTO.getUsuarioId())
               .orElseThrow(()->new ResourceNotFoundException("Usuario apoderado no encontrado!!!"));

       Mensualidad mensualidad = mensualidadMapper.toEntity(mensualidadRequestDTO);
       mensualidad.setUsuario(user);

       mensualidadRepository.save(mensualidad);

       return mensualidadMapper.toResponseDTO(mensualidad);

   }

   @Transactional
   public MensualidadResponseDTO updateMensualidad(UpdateMensualidadRequestDTO updateMensualidadRequestDTO, Long id){
        // se obtiene la mensualidad a modificar

       Mensualidad mensualidadActual = mensualidadRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Mensualidad no encontrada!!!"));

       Mensualidad mensualidadUpdated;

       if(updateMensualidadRequestDTO.getMonto() != 0) mensualidadActual.setMonto(updateMensualidadRequestDTO.getMonto());
       if(updateMensualidadRequestDTO.getFechaVencimiento() != null) mensualidadActual.setFechaVencimiento(updateMensualidadRequestDTO.getFechaVencimiento());
       if(updateMensualidadRequestDTO.getEstado() != null) mensualidadActual.setEstado(updateMensualidadRequestDTO.getEstado());

       mensualidadUpdated = mensualidadRepository.save(mensualidadActual);

       return mensualidadMapper.toResponseDTO(mensualidadUpdated);
   }

   @Transactional
   public void deleteMensualidad(Long id){
        Mensualidad mensualidad = mensualidadRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Mensualidad not found with id: " + id));

     mensualidad.setEnabled(false);
     mensualidadRepository.save(mensualidad);
   }

   @Transactional
   public void reactivarMensualidad(Long id){

       Mensualidad mensualidad = mensualidadRepository.findById(id)
               .orElseThrow(()->new ResourceNotFoundException("Mensualidad not found with id: " + id));

       if(mensualidad.isEnabled()){
           throw new BadRequestException("La mensualidad con id " +"  "+ id + " ya está activada.");
       }

       mensualidad.setEnabled(true);
       mensualidadRepository.save(mensualidad);

   }

   /*	@Transactional(readOnly = true)
	public FurgonResponseDTO getMyFurgon(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();

		User user = userRepository.findOneByEmail(email)
				.orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));

		Furgon furgon = furgonRepository.findByUsuarioTransportista_Id(user.getId())
				.orElseThrow(()-> new ResourceNotFoundException("No tienes un furgon asignado"));
		return furgonMapper.toResponseDTO(furgon);
	}
   * */

}

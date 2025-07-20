package com.patricio.contreras.service;

import com.patricio.contreras.domain.entity.Mensualidad;
import com.patricio.contreras.dto.resquest.PagoRequestDTO;
import com.patricio.contreras.dto.resquest.UpdatePagoRequestDTO;
import com.patricio.contreras.exception.BadRequestException;
import com.patricio.contreras.repository.MensualidadRepository;
import com.patricio.contreras.security.UserDetailsServiceImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.patricio.contreras.domain.entity.Pago;
import com.patricio.contreras.domain.enums.Estado;
import com.patricio.contreras.dto.response.PagoResponseDTO;
import com.patricio.contreras.exception.ResourceNotFoundException;
import com.patricio.contreras.mapper.PagoMapper;
import com.patricio.contreras.repository.PagoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PagoService {
	
	private final PagoRepository pagoRepository;
	
	private final PagoMapper pagoMapper;

	private final MensualidadRepository mensualidadRepository;
	//quede aca
/**/


	@Transactional(readOnly = true)
	public Page<PagoResponseDTO> getAllPagos(Pageable pageable){
		Page<Pago> pagos = pagoRepository.findAllEnabled(pageable);
		
		return pagos.map(pagoMapper::toResponseDTO);
		
	
	}
	@Transactional(readOnly = true)
	public PagoResponseDTO getPagoById(Long id) {
		Pago pago = pagoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Pago not found with id:" + id));
		return pagoMapper.toResponseDTO(pago);
	}

	@Transactional
	public PagoResponseDTO createPago(PagoRequestDTO pagoRequestDTO){

		Mensualidad mensualidad = mensualidadRepository.findById(pagoRequestDTO.getMensualidadId())
				.orElseThrow(()-> new ResourceNotFoundException("Mensualidad no encontrada!!!"));
		Pago pago =  pagoMapper.toEntity(pagoRequestDTO);
		pago.setMensualidad(mensualidad);
        pago.calcularMulta();
		pagoRepository.save(pago);

		return pagoMapper.toResponseDTO(pago);
	}

	@Transactional
	public PagoResponseDTO updatePago(UpdatePagoRequestDTO updatePagoRequestDTO, Long id){

		Pago pagoActual = pagoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Pago no encontrado!!"));

		Pago pagoUpdated;

		if(updatePagoRequestDTO.getMontoPagado() != 0) pagoActual.setMontoPagado(updatePagoRequestDTO.getMontoPagado());
		if(updatePagoRequestDTO.getFechaPago() != null) pagoActual.setFechaPago(updatePagoRequestDTO.getFechaPago());

		pagoUpdated = pagoRepository.save(pagoActual);

		return pagoMapper.toResponseDTO(pagoUpdated);
	}

	@Transactional
	public void deletePago(Long id){

		Pago pago = pagoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Mensualidad not found with id: " + id));
		pago.setEnabled(false);
		pagoRepository.save(pago);
	}

	@Transactional
	public void  reactivarPago(Long id){

		Pago pago = pagoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Mensualidad not found with id: " + id));

		if(pago.isEnabled()){
			throw new BadRequestException("El pago con id " +"  "+ id + " ya está activado.");
		}

		pago.setEnabled(true);
		pagoRepository.save(pago);
	}






}

package com.patricio.contreras.service;

import com.patricio.contreras.dto.resquest.PagoRequestDTO;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
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
	
	//quede aca
/**/
	
	@Transactional(readOnly = true)
	public Page<PagoResponseDTO> getAllPagos(Pageable pageable){
		Page<Pago> pagos = pagoRepository.findAll(pageable);
		
		return pagos.map(pagoMapper::toResponseDTO);
		
	
	}
	
	@Transactional(readOnly = true)
	public Page<PagoResponseDTO> getPagoByState(Estado estado,Pageable pageable){
		Page<Pago> pagos = pagoRepository.findByEstado(estado,pageable);
		return pagos.map(pagoMapper::toResponseDTO);
		 
	}
	
	
	public PagoResponseDTO getPagoById(Long id) {
		Pago pago = pagoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Pago not found with id:" + id));
		return pagoMapper.toResponseDTO(pago);
	}
	
	@Transactional(readOnly = true)
	public Page<PagoResponseDTO>getPagoByUserId(Long id,Pageable pageable){
		Page<Pago> pagosByIdUser = pagoRepository.findByUserId(id,pageable);
		return pagosByIdUser.map(pagoMapper::toResponseDTO);
		
	}
	@Transactional
	public PagoResponseDTO createPago(PagoRequestDTO pagoRequestDTO){
		Pago pago = pagoMapper.toEntity(pagoRequestDTO);
		pago.setMonto(pago.getMonto());
		pago.setFechaVencimiento(pago.getFechaVencimiento());
		pago.setEstado(pago.getEstado());
		pago.setUsuario(pago.getUsuario());
		pago.getMontoConMulta();
		pago = pagoRepository.save(pago);

		return pagoMapper.toResponseDTO(pago);
	}
	
	
	
	

}

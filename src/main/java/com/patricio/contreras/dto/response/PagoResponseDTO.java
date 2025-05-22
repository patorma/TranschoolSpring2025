package com.patricio.contreras.dto.response;

import java.util.Date;

import com.patricio.contreras.domain.enums.Estado;

import lombok.Data;

@Data
public class PagoResponseDTO {

	private Long id;
	
	private int monto;
	
	private Date fechaVencimiento;
	
	private Date fechaPago;
	
	private int totalPago;
	
	private Estado estado;
	
	private String usuarioNombres;
	private Long usuarioId;
}

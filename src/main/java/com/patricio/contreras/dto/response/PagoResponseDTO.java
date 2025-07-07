package com.patricio.contreras.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Estado;

import lombok.Data;

@Data
public class PagoResponseDTO {

	private Long id;
	
	private int monto;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaVencimiento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaPago;
	
	private int totalPago;
	
	private Estado estado;
	
	private String usuarioNombres;
	private Long usuarioId;
}

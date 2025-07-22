package com.patricio.contreras.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patricio.contreras.domain.enums.Estado;

import lombok.Data;

@Data
public class PagoResponseDTO {

	private Long id;
	
	private int montoPagado;


	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date fechaPago;


	private Long mensualidadId;

}

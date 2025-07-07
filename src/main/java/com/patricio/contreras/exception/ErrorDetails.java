package com.patricio.contreras.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

//clase tipo dto para mandarle al front ciertos mensajes de error 

@Data
@AllArgsConstructor
public class ErrorDetails {

	private LocalDateTime timestamp;
	
	private String message;  //mensaje del error 
	
	private String details; // detalle del error 
}

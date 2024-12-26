package com.patricio.contreras.domain.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patricio.contreras.domain.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagos")
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "monto",nullable = false)
	private int monto;
	
	@Column(name = "fecha_vencimiento",nullable = false)
	private Date fechaVencimiento; 
	
	@Column(name = "fecha_pago",nullable = true)
	private Date fechaPago;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private User usuario;

}

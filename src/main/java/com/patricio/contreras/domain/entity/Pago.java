package com.patricio.contreras.domain.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.patricio.contreras.domain.enums.Estado;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pagos")
public class Pago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "monto_pagado",nullable = true)
	private int montoPagado;

	@Column(name = "fecha_pago",nullable = false)
	@Temporal(TemporalType.DATE)
	private  Date fechaPago;

	@OneToOne
	@ToString.Exclude
	@JoinColumn(name = "mensualidad_id", nullable = false, unique = true)
	private Mensualidad mensualidad;


	public int calcularMulta(){
		Date fechaVencimiento = mensualidad.getFechaVencimiento();
		long diferenciaEnMs = fechaPago.getTime() - fechaVencimiento.getTime();
		long diasRetraso = TimeUnit.DAYS.convert(diferenciaEnMs, TimeUnit.MILLISECONDS);

		if (diasRetraso > 10) {
			double multa = mensualidad.getMonto() * 0.10; // 10%
			this.montoPagado  = mensualidad.getMonto()+ (int) multa;

			return this.montoPagado;
		}
		this.montoPagado = mensualidad.getMonto();
		return this.montoPagado;
	}
	
}

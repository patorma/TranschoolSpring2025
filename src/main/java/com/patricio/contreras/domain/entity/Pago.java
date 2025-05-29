package com.patricio.contreras.domain.entity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "no puede estar vacia la fecha")
	@Temporal(TemporalType.DATE)
	private Date fechaVencimiento; 
	
	@Column(name = "fecha_pago",nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaPago;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private User usuario;
	
	@Column(name = "total_pago",nullable = false)
	private int totalPago;

	public int getMontoConMulta() {
		 if (fechaPago == null) {
			 this.totalPago = monto;
		        return this.totalPago ; // Si aún no se ha pagado, devolver el monto original
		    }
		 long diferenciaEnMs = fechaPago.getTime() - fechaVencimiento.getTime();
		    long diasRetraso = TimeUnit.DAYS.convert(diferenciaEnMs, TimeUnit.MILLISECONDS);

		    if (diasRetraso > 10) {
		        double multa = monto * 0.10; // 10% de multa
		        this.totalPago =  monto + (int) multa;
		        return this.totalPago;
		    }

		    return this.totalPago;
	}
	
}

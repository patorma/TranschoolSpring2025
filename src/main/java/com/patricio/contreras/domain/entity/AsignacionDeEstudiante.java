package com.patricio.contreras.domain.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="asignaciones_de_estudiantes")
public class AsignacionDeEstudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_registro",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	@ManyToOne
	@JoinColumn(name = "furgon_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Furgon furgon;
	
	@ManyToOne
	@JoinColumn(name = "estudiante_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Estudiante estudiante;

	@ManyToOne
	@JoinColumn(name = "recorrido_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Recorrido recorrido;

}

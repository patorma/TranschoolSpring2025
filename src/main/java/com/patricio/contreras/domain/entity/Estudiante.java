package com.patricio.contreras.domain.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiantes")
public class Estudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombres" , nullable = false)
	private String nombres;
	
	@Column(name = "apellidos",nullable = false)
	private String apellidos;
	
	@Column(name = "fecha_nacimiento",nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Column(name = "colegio",nullable = false)
	private String colegio;

	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private User usuario;
	
	@OneToOne(mappedBy = "estudiante")
	private Recorrido recorrido;

}

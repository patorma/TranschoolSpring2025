package com.patricio.contreras.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recorridos", uniqueConstraints = {
		@UniqueConstraint(name = "uq_recorrido_origen", columnNames = {"origen"})
})
public class Recorrido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Column(nullable = false)
	private String origen;

	@Column(nullable = false)
	private String destino;

	@Column(name = "descripcion")
	private String descripcion;
	
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estudiante_id",nullable = false,referencedColumnName = "id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","hadler"})
	private Estudiante estudiante;*/

}

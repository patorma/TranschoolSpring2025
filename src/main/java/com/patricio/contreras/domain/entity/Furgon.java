package com.patricio.contreras.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "furgones")
public class Furgon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "patente", nullable = false,unique = true)
	@NotNull
	@Size(min = 4)
	private String patente;
	
	@Column( name = "descripcion",nullable = false)
	@NotNull
	@Size(min = 20)
	private String descripcion;

//esta entidad tendra el id del usuario transportista asignado
	@OneToOne
	@JoinColumn(name = "usuario_transportista_id", nullable = false, unique = true)
	private User usuarioTransportista;

	@Column(name = "enabled",nullable = false)
	private boolean enabled = true; //habilita o desactiva el furgon para soft delete

}

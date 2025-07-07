package com.patricio.contreras.domain.entity;

import com.patricio.contreras.domain.enums.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombres" , nullable = false)
	private String nombres;
	
	@Column(name = "apellidos",nullable = false)
	private String apellidos;
	
	@Column(name = "comuna",nullable = false)
	private String comuna;
	
	@Column(name = "telefono",nullable = false)
	private String telefono;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "role",nullable=false)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToOne(mappedBy = "usuarioTransportista",fetch = FetchType.LAZY)
	private Furgon furgon;
}

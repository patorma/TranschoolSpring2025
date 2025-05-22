package com.patricio.contreras.domain.entity;

import com.patricio.contreras.domain.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}

package com.patricio.contreras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.domain.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
	
   Optional<Estudiante> findByNombres(String nombres);

}

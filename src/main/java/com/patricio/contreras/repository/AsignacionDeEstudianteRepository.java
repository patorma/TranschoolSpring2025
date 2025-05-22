package com.patricio.contreras.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patricio.contreras.domain.entity.AsignacionDeEstudiante;

public interface AsignacionDeEstudianteRepository extends JpaRepository<AsignacionDeEstudiante, Long> {

	
	@Query("SELECT as FROM AsignacionDeEstudiante as WHERE as.estudiante.id = :estudianteId")
	Page<AsignacionDeEstudiante> findByEstudianteId(@Param("estudianteId") Long estudianteId,Pageable pageable);
	
	@Query("SELECT as FROM AsignacionDeEstudiante as WHERE as.furgon.id = :furgonId")
	Page<AsignacionDeEstudiante>findByFurgon(@Param("furgonId") Long furgonId, Pageable pageable);
}

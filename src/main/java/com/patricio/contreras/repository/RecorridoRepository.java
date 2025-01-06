package com.patricio.contreras.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patricio.contreras.domain.entity.Recorrido;

public interface RecorridoRepository extends JpaRepository<Recorrido, Long> {
	
	
	@Query("SELECT r FROM Recorrido r WHERE r.estudiante.id = :estudianteId")
	Page<Recorrido> findByEstudianteId(@Param("estudianteId") Long estudianteId,Pageable pageable);
	
	@Query("SELECT r FROM Recorrido r WHERE r.furgon.id = :furgonId")
    Page<Recorrido> findByFurgonId(@Param("furgonId") Long furgonId, Pageable pageable);
}

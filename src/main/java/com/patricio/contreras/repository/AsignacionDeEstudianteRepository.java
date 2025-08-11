package com.patricio.contreras.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.patricio.contreras.domain.entity.AsignacionDeEstudiante;

import java.util.Optional;

public interface AsignacionDeEstudianteRepository extends JpaRepository<AsignacionDeEstudiante, Long> {
//estudiante_id   furgon_id
	boolean existsByEstudiante_Id(Long id);
	boolean existsByFurgon_Id(Long id);
	@Query("SELECT as FROM AsignacionDeEstudiante as WHERE as.estudiante.id = :estudianteId")
	Optional<AsignacionDeEstudiante> findByEstudianteId(@Param("estudianteId") Long estudianteId);

	@Query("SELECT as FROM AsignacionDeEstudiante as WHERE as.recorrido.id = : recorridoId")
	Optional<AsignacionDeEstudiante> findByRecorridoId(@Param("recorridoId") Long recorridoId);


	@Query("SELECT as FROM AsignacionDeEstudiante as WHERE as.furgon.id = :furgonId")
	Page<AsignacionDeEstudiante>findByFurgon(@Param("furgonId") Long furgonId, Pageable pageable);

	@Query(value = "SELECT contar_estudiantes_asignados(:furgonId)", nativeQuery = true)
	Integer contarEstudiantesAsignados(@Param("furgonId") Long furgonId);

}

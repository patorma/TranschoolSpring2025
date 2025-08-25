package com.patricio.contreras.repository;

import com.patricio.contreras.domain.entity.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.patricio.contreras.domain.entity.AsignacionDeEstudiante;

import java.util.List;
import java.util.Optional;

public interface AsignacionDeEstudianteRepository extends JpaRepository<AsignacionDeEstudiante, Long> {
//estudiante_id   furgon_id
	boolean existsByEstudiante_Id(Long id);
	boolean existsByFurgon_Id(Long id);
	@Query("SELECT asig FROM AsignacionDeEstudiante asig WHERE asig.estudiante.id = :estudianteId")
	Optional<AsignacionDeEstudiante> findByEstudianteId(@Param("estudianteId") Long estudianteId);

	@Query("SELECT asig FROM AsignacionDeEstudiante asig WHERE asig.recorrido.id = : recorridoId")
	Optional<AsignacionDeEstudiante> findByRecorridoId(@Param("recorridoId") Long recorridoId);
    // como se consultaran varias tablas para la informacion se ocupara esto
    @Query(value = "SELECT (e.id) AS id ,(e.nombres) AS nombres,(e.apellidos) AS apellidos,(e.colegio)" +
            " AS colegio,(e.email) AS email,(e.fecha_nacimiento) AS fecha_nacimiento,(e.user_id) AS user_id FROM Users u INNER JOIN" +
            " Furgones f ON(u.id = f.usuario_transportista_id) INNER JOIN " +
            "Asignaciones_De_Estudiantes asig ON(f.id = asig.furgon_id) INNER JOIN Estudiantes e " +
            "ON(asig.estudiante_id = e.id) WHERE u.id= ?1 order by e.nombres",nativeQuery = true)
    List<Estudiante> obtenerEstudiantesTransportista(@Param("u.id") Long idTransportista);

	@Query("SELECT asig FROM AsignacionDeEstudiante asig WHERE asig.furgon.id = :furgonId")
	Page<AsignacionDeEstudiante>findByFurgon(@Param("furgonId") Long furgonId, Pageable pageable);

	@Query(value = "SELECT contar_estudiantes_asignados(:furgonId)", nativeQuery = true)
	Integer contarEstudiantesAsignados(@Param("furgonId") Long furgonId);

}

package com.patricio.contreras.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patricio.contreras.domain.entity.Recorrido;

import java.util.List;

public interface RecorridoRepository extends JpaRepository<Recorrido, Long> {
	
	
	/*@Query("SELECT r FROM Recorrido r WHERE r.estudiante.id = :estudianteId")
	Page<Recorrido> findByEstudianteId(@Param("estudianteId") Long estudianteId,Pageable pageable);*/


    @Query(value = "SELECT (r.id) AS id,(r.origen) AS origen, (r.destino) AS destino," +
            "(r.descripcion) AS descripcion FROM Users u INNER JOIN Furgones f " +
            "ON(u.id = f.usuario_transportista_id) INNER JOIN  Asignaciones_De_Estudiantes " +
            "asig ON(f.id = asig.furgon_id) INNER JOIN Recorridos r ON(asig.recorrido_id = r.id ) " +
            "WHERE u.id = ?1",nativeQuery = true)
    List<Recorrido> obtenerRecorridosTransportista( @Param("u.id") Long idTransportista);

    boolean existsByOrigen(String origen);
    boolean existsByDestino(String destino);
    boolean existsByDescripcion(String descripcion);
}

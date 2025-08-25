package com.patricio.contreras.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.patricio.contreras.domain.entity.Furgon;
import org.springframework.data.jpa.repository.Query;


public interface FurgonRepository extends JpaRepository<Furgon, Long>{

	
	
	Optional<Furgon> findByPatente (String furgones);
//quede aca
	Optional<Furgon> findByUsuarioTransportista_Id(Long usuarioId);

	boolean existsByPatente(String patente);

	boolean existsByUsuarioTransportista_Id(Long usuarioTransportistaId);

	@Query("SELECT f FROM Furgon f WHERE f.enabled=true")
	List<Furgon> findAllEnabled();

    @Query(value = "SELECT  id, patente, descripcion, usuario_transportista_id,enabled " +
            "FROM Furgones WHERE id NOT IN (SELECT furgon_id FROM Asignaciones_De_Estudiantes)",nativeQuery = true)
    List<Furgon> furgonesSinAsignacion();
}

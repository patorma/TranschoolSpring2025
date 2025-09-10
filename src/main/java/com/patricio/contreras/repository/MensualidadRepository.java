package com.patricio.contreras.repository;

import com.patricio.contreras.domain.entity.Mensualidad;
import com.patricio.contreras.domain.enums.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MensualidadRepository extends JpaRepository<Mensualidad,Long> {


    @Query("SELECT m FROM Mensualidad m WHERE m.usuario.id = :usuarioId AND  m.enabled=true")
    Page<Mensualidad> findByUsuarioId(@Param("usuarioId") Long usuarioId, Pageable pageable);

    @Query("SELECT m FROM Mensualidad m WHERE m.usuario.id = :usuarioId AND  m.enabled=true")
    Page<Mensualidad> findByUsuarioIdMy(Long usuarioId,Pageable pageable);//ver mensualidades de un apoderado

    @Query("SELECT m FROM Mensualidad m WHERE m.estado = :estado")
    Page<Mensualidad> findByEstado(@Param("estado") Estado estado,Pageable pageable);

    @Query("SELECT m FROM Mensualidad m  WHERE m.enabled=true")
    Page<Mensualidad> findAllEnabled(Pageable pageable);
    /*r	@Query("SELECT f FROM Furgon f WHERE f.enabled=true")
	List<Furgon> findAllEnabled();*/

}

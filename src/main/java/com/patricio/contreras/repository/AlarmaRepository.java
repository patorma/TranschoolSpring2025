package com.patricio.contreras.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.patricio.contreras.domain.entity.Alarma;

public interface AlarmaRepository extends JpaRepository<Alarma, Long> {
	
	@Query("SELECT a FROM Alarma a WHERE a.usuario.id=:userId")
	Page<Alarma> findByUserId(@Param("userId") Long userId,Pageable pageable);
	
	@Query("SELECT a FROM Alarma a WHERE a.tipoAlarma.nombreAlarma = :tipoAlarmaName")
	Page<Alarma> findByTipoAlarmaName(@Param("tipoAlarmaName") String tipoAlarmaName,Pageable pageable );

}

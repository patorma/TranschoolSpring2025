package com.patricio.contreras.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.patricio.contreras.domain.entity.Pago;
import com.patricio.contreras.domain.enums.Estado;

public interface PagoRepository extends JpaRepository<Pago, Long> {

	//Optional<Pago> findByEstado(String estado);
	
	/*
	 * @Query("SELECT a FROM Alarma a WHERE a.tipoAlarma.nombreAlarma = :tipoAlarmaName")
	Page<Alarma> findByTipoAlarmaName(@Param("tipoAlarmaName") String tipoAlarmaName,Pageable pageable );
	 * 
	 * */
	@Query("SELECT p FROM Pago p WHERE p.estado = :estado")
	Page<Pago> findByEstado(@Param("estado") Estado estado,Pageable pageable);
	
	@Query("SELECT p FROM Pago p WHERE p.usuario.id = :userId")
    Page<Pago> findByUserId(@Param("userId") Long userId,Pageable pageable);


	
}
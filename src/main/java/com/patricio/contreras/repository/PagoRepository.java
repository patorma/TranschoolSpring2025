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


    @Query("SELECT p FROM Pago p  WHERE p.enabled= true")
    Page<Pago> findAllEnabled(Pageable pageable);



	
}
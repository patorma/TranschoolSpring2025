package com.patricio.contreras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.domain.entity.TipoAlarma;

public interface TipoAlarmaRepository extends JpaRepository<TipoAlarma, Long> {

}

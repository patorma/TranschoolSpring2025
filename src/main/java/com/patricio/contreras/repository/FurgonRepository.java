package com.patricio.contreras.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.patricio.contreras.domain.entity.Furgon;


public interface FurgonRepository extends JpaRepository<Furgon, Long>{

	
	
	Optional<Furgon> findByPatente (String furgones);
}

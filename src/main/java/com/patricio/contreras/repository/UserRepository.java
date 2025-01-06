package com.patricio.contreras.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.domain.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
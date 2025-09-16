package com.patricio.contreras.repository;
import java.util.List;
import java.util.Optional;

import com.patricio.contreras.domain.enums.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;

public interface UserRepository extends JpaRepository<User,Long>{

	//Buscar usuario por correo
		Optional<User> findOneByEmail(String email);
		
		//Verificar si el usuario existe mediante un correo
		boolean existsByEmail(String email);

	  Page<User> findByRoleNot(Role role, Pageable pageable);

	  List<User> findByRole(Role role);

      @Query(value = "SELECT id, nombres,apellidos, comuna,email,role,password,telefono " +
              "FROM Users  WHERE role='TRANSPORTISTA' AND id NOT IN" +
              " (SELECT usuario_transportista_id  FROM Furgones)",nativeQuery = true)
      List<User> transportistasSinFurgon();
}
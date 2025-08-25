package com.patricio.contreras.repository;
import java.util.List;
import java.util.Optional;

import com.patricio.contreras.domain.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.patricio.contreras.domain.entity.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long>{

	//Buscar usuario por correo
		Optional<User> findOneByEmail(String email);
		
		//Verificar si el usuario existe mediante un correo
		boolean existsByEmail(String email);

	  List<User> findByRoleNot( Role role);
	  List<User> findByRole(Role role);

      @Query(value = "SELECT id, nombres,apellidos, comuna,email,role,password,telefono " +
              "FROM Users  WHERE role='TRANSPORTISTA' AND id NOT IN" +
              " (SELECT usuario_transportista_id  FROM Furgones)",nativeQuery = true)
      List<User> transportistasSinFurgon();
}
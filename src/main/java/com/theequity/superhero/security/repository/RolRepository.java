package com.theequity.superhero.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theequity.superhero.security.entity.Rol;
import com.theequity.superhero.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	Optional<Rol> findByRolNombre (RolNombre rolNombre);

}

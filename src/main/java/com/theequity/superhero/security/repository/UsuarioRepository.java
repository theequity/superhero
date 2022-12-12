package com.theequity.superhero.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theequity.superhero.security.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	boolean existsByNombreUsuario(String NombreUsuario);
	boolean existsByEmail(String email);
	
	

}

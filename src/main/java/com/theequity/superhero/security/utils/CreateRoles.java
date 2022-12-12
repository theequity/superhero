package com.theequity.superhero.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.theequity.superhero.security.entity.Rol;
import com.theequity.superhero.security.entity.RolService;
import com.theequity.superhero.security.enums.RolNombre;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RolService rolService;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
		Rol rolUser = new Rol(RolNombre.ROLE_USER);
		rolService.save(rolAdmin);
		rolService.save(rolUser);
	}

}

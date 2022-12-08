package com.theequity.superhero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.theequity.superhero.entity.Superhero;




@Service
public interface SuperheroService {
	
	
	public List<Superhero> findAllSuperheroes();

	public Superhero findSuperheroById(Long id);
	
	public Superhero createSuperhero(Superhero superhero);
	
	public Superhero updateSuperhero(Long id, Superhero superhero);
	
	public void deleteSuperhero(Long id);

	public List<Superhero> findSuperheroContaninign(String inChart);
	
	

}

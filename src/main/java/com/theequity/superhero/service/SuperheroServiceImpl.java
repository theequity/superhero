package com.theequity.superhero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.theequity.superhero.entity.Superhero;
import com.theequity.superhero.repository.SuperheroRepository;

@CacheConfig ( cacheNames = "superhero" )
@Service
public class SuperheroServiceImpl implements SuperheroService {

	@Autowired
	private SuperheroRepository superheroRepository;

	
	@Cacheable(value = "allsuperherocache")
	@Override
	public List<Superhero> findAllSuperheroes() {
		return superheroRepository.findAll();
	}

	
	@Cacheable(value = "allsuperherocache",key = "#id")
	@Override
	public Superhero findSuperheroById(Long id) {
		return superheroRepository.findById(id).orElse(null);
	}

	@Caching(evict = {
			@CacheEvict(value = "allsuperherocache", allEntries = true ),
			@CacheEvict(value = "superherocache", key = "#superhero.id")})
	@Override
	public Superhero createSuperhero(Superhero superhero) {
		return superheroRepository.save(superhero);
	}

	@Caching(
            put= @CachePut(value="allsuperherocache"),
            cacheable = @Cacheable(value = "allsuperherocache"))
           
	@Override
	public Superhero updateSuperhero(Long id, Superhero superh) {
		Superhero heroe = null;
		Optional<Superhero> optional = superheroRepository.findById(id);
		if (optional.isPresent()) {
			heroe = optional.get();
			heroe.setFirstName(superh.getFirstName());
			heroe.setLastName(superh.getLastName());
			heroe.setSuperheroName(superh.getSuperheroName());
			superheroRepository.save(heroe);
		}
		return heroe;
	}

	@Caching(evict = { 
			@CacheEvict(value = "allsuperherocache", allEntries = true),
			@CacheEvict(value = "superherocache", key = "#id") 
			})
	@Override
	public void deleteSuperhero(Long id) {
		Optional<Superhero> optional = superheroRepository.findById(id);
		if (optional.isPresent()) {
			superheroRepository.deleteById(id);
		}
	}

	@Cacheable(value = "allsuperherocache",key = "#inChart")
	@Override
	public List<Superhero> findSuperheroContaninign(String inChart) {
		return superheroRepository.findBySuperheroNameContainingIgnoreCase(inChart);
	}
	


}

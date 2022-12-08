package com.theequity.superhero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theequity.superhero.dto.SuperheroDto;
import com.theequity.superhero.entity.Superhero;
import com.theequity.superhero.mapper.SuperheroMapper;
import com.theequity.superhero.service.SuperheroService;


@RestController
@RequestMapping("/api/superheroes")
public class SuperheroController {
	
	@Autowired
	private SuperheroService superheroService;

	@GetMapping
	public ResponseEntity<List<SuperheroDto>> getSuperheroes() {
		List<Superhero> superheroes = superheroService.findAllSuperheroes();
		 if(superheroes.isEmpty())
			 return ResponseEntity.noContent().build();
		 return ResponseEntity.ok(SuperheroMapper.MAPPER.toListSuperheroDto(superheroes));
	}
	
	@GetMapping("/{superheroId}")
	public ResponseEntity<SuperheroDto> getSuperheroById(@PathVariable Long superheroId) {
		Superhero superhero = superheroService.findSuperheroById(superheroId);
		if(superhero == null) 
			return  ResponseEntity.notFound().build();
		return ResponseEntity.ok(SuperheroMapper.MAPPER.toSuperheroDto(superhero));
		
	}
	
	@GetMapping("/contains/{in}")
	public ResponseEntity<List<SuperheroDto>> getSuperheroContaninign(@PathVariable String in) {
		List<Superhero> superheroes = superheroService.findSuperheroContaninign(in);
		 if(superheroes.isEmpty())
			 return ResponseEntity.noContent().build();
		 return ResponseEntity.ok(SuperheroMapper.MAPPER.toListSuperheroDto(superheroes));
	}
//	@GetMapping("/contains/{in}")
//	public ResponseEntity<List<SuperheroDto>> getSuperheroContaninign(@RequestParam String in) {
//		List<Superhero> superheroes = superheroService.findSuperheroContaninign(in);
//		 if(superheroes.isEmpty())
//			 return ResponseEntity.noContent().build();
//		 return ResponseEntity.ok(SuperheroMapper.MAPPER.toListSuperheroDto(superheroes));
//	}
	
	@PostMapping()
	public Superhero createSuperhero(@RequestBody SuperheroDto superheroDto) {
		return superheroService.createSuperhero(SuperheroMapper.MAPPER.toSuperhero(superheroDto));
	}
	
	@PutMapping("/{superheroId}")
	public ResponseEntity<SuperheroDto>  updateSuperhero(@PathVariable Long superheroId, @RequestBody  SuperheroDto superheroDto) {
		Superhero hero = superheroService.updateSuperhero(superheroId, SuperheroMapper.MAPPER.toSuperhero(superheroDto));
		if(hero == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(SuperheroMapper.MAPPER.toSuperheroDto(hero));
	}

	@DeleteMapping("/{superheroId}")
	public ResponseEntity<?> deleteSuperhero(@PathVariable Long superheroId) {
		superheroService.deleteSuperhero(superheroId);
		return ResponseEntity.noContent().build();
	}

}

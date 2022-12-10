package com.theequity.superhero.unittest.it;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.theequity.superhero.entity.Superhero;
import com.theequity.superhero.repository.SuperheroRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


@DataJpaTest
public class SuperheroControllerDataJpaTestIT {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SuperheroRepository superheroRepository;
	
	

	@Test
	public void whenFindById_thenReturnSuperhero() {
		// given
		Superhero superHero = createSuperhero();

		entityManager.persist(superHero);
		entityManager.flush();

		// when
		Superhero sh = superheroRepository.findById(superHero.getId()).orElseThrow();

		// then
		assertThat(sh.getSuperheroName()).isEqualTo(superHero.getSuperheroName());
	}

	@Test
	public void whenFindAllSuperhero_thenReturnSuperheroList() {
		// given
		List<Superhero> superHeroList = createSuperheroList();

		superHeroList.forEach(entityManager::persist);

		entityManager.flush();

		// when
		List<Superhero> superheroes = superheroRepository.findAll();

		// then
		assertThat(superHeroList).hasSameElementsAs(superheroes);
		assertThat(superheroes).size().isGreaterThan(0);
	}
	
	@Test
	public void whenCreateSuperhero_thenReturnNewSuperhero() {
		// given
		Superhero superHero = createSuperhero();
		
		// when
		Superhero createdSuperhero = superheroRepository.save(superHero);

		// then
		assertNotNull(createdSuperhero.getId());
		assertEquals(createdSuperhero, superHero);
	
	}
	
	
	@Test
	public void whenUpdateSuperhero_thenReturnUpdatedSuperhero() {
		// given
		Superhero superHero = createSuperhero();
		
		entityManager.persist(superHero);
		entityManager.flush();
		
		// when
		Superhero createdSuperhero = superheroRepository.save(superHero);

		// then
		assertNotNull(createdSuperhero.getId());
		assertEquals(createdSuperhero, superHero);
	
	}
	
	

	private List<Superhero> createSuperheroList() {

		Superhero sh = new Superhero();
		sh.setFirstName("Bruno");
		sh.setLastName("Díaz");
		sh.setSuperheroName("Batman");

		Superhero sh2 = new Superhero();
		sh2.setFirstName("Klar");
		sh2.setLastName("Kent");
		sh2.setSuperheroName("Superman");

		Arrays.asList(sh, sh2);

		return Arrays.asList(sh, sh2);

	}

	private Superhero createSuperhero() {

		Superhero superhero = new Superhero();
		superhero.setFirstName("Bruno");
		superhero.setLastName("Díaz");
		superhero.setSuperheroName("Batman");

		return superhero;

	}

}
package com.theequity.superhero.unittest.it;

import static org.assertj.core.api.Assertions.assertThat;

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
		Superhero superHero = createBook();

		entityManager.persist(superHero);
		entityManager.flush();

		// when
		Superhero sh = superheroRepository.findById(1L).orElseThrow();

		// then
		assertThat(sh.getSuperheroName()).isEqualTo(superHero.getSuperheroName());
	}

	private Superhero createBook() {

		Superhero sh = new Superhero();
		sh.setFirstName("Bruno");
		sh.setLastName("Díaz");
		sh.setSuperheroName("Batman");

		return sh;

	}

}
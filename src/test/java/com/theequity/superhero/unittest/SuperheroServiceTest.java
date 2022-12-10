package com.theequity.superhero.unittest;

import static org.mockito.Mockito.when;

import com.theequity.superhero.entity.Superhero;
import com.theequity.superhero.repository.SuperheroRepository;
import com.theequity.superhero.service.SuperheroService;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperheroServiceTest {

  @Autowired
  private SuperheroService superheroService;

  @MockBean
  private SuperheroRepository superheroRepository;

  @Test
  public void testRetrieveSuperheroWithMockRepository() throws Exception {

    Optional<Superhero> optSuperhero = Optional.of(createSuperhero());

    when(superheroRepository.findById(1L)).thenReturn(optSuperhero);

    assert superheroService.findSuperheroById(1L).getSuperheroName().contains("Batman");

  }

	private Superhero createSuperhero() {

		Superhero superhero = new Superhero();
		superhero.setFirstName("Bruno");
		superhero.setLastName("Díaz");
		superhero.setSuperheroName("Batman");

		return superhero;

	}
}
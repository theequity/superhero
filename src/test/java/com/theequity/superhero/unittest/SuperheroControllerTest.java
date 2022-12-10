package com.theequity.superhero.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theequity.superhero.controller.SuperheroController;
import com.theequity.superhero.entity.Superhero;
import com.theequity.superhero.service.SuperheroService;

@WebMvcTest(SuperheroController.class)
public class SuperheroControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SuperheroService superheroService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenSuperheroes_whenGetSuperheroeById_thenReturnSuperheroe() throws Exception {
		
		Superhero superhero = createSuperhero();

		Mockito.when(superheroService.findSuperheroById(1L)).thenReturn(superhero);

		MvcResult findSuperHeroById = mockMvc.perform(get("/api/superheroes/1")
				.accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		
		Superhero superHe = objectMapper.readValue(findSuperHeroById.getResponse().getContentAsString(), Superhero.class);
		
		assert superHe.getSuperheroName().equalsIgnoreCase("Batman");
	}

	private Superhero createSuperhero() {
		Superhero superhero = new Superhero();
		superhero.setFirstName("Bruno");
		superhero.setLastName("Díaz");
		superhero.setSuperheroName("Batman");
		return superhero;
	}
}
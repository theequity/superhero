package com.theequity.superhero.unittest.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theequity.superhero.entity.Superhero;

@AutoConfigureMockMvc
@SpringBootTest
public class SuperheroControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void findById() throws Exception {
		 Superhero superhero = createSuperhero();
		mockMvc.perform(MockMvcRequestBuilders.post("/api/superheroes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(superhero)))
				.andExpect(status().isOk());
		 MvcResult findById = mockMvc.perform(get("/api/superheroes/1")
				 .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
				 .andExpect(status().isOk()).andReturn();
		 Superhero supHe = objectMapper.readValue(findById.getResponse().getContentAsString(), Superhero.class);
		assert supHe.getSuperheroName().equalsIgnoreCase("Batman");
	}

	private Superhero createSuperhero() {

		Superhero superhero = new Superhero();
		superhero.setFirstName("Bruno");
		superhero.setLastName("Díaz");
		superhero.setSuperheroName("Batman");

		return superhero;

	}
}

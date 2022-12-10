package com.theequity.superhero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperheroDto {
	
	
	private String firstName;

	private String lastName;

	private String superheroName;
}

package com.theequity.superhero.entity;


import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="superheroes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Superhero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	private String superheroName;

	


}

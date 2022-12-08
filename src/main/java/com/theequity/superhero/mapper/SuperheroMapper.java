package com.theequity.superhero.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.theequity.superhero.dto.SuperheroDto;
import com.theequity.superhero.entity.Superhero;



@Mapper
public interface SuperheroMapper {
	
	SuperheroMapper MAPPER = Mappers.getMapper(SuperheroMapper.class);


	SuperheroDto toSuperheroDto(Superhero superhero);
	
	Superhero toSuperhero (SuperheroDto superheroDto);
	
	List<SuperheroDto> toListSuperheroDto(List<Superhero> superheroList);
	
	List<Superhero> toListSuperhero (List<SuperheroDto> superheroDtoList);
	

}

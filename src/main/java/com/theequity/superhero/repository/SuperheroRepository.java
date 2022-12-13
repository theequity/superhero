package com.theequity.superhero.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theequity.superhero.entity.Superhero;




@Transactional
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

	public List<Superhero> findBySuperheroNameContainingIgnoreCase(String inChart);


}

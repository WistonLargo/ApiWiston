package com.example.apiwistonspring.populate;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.apiwistonspring.model.repositories.MovilRepository;

public class Populate {
	@Autowired
	private final MovilRepository movilRepository;

	public Populate(MovilRepository movilRepository) {
		super();
		this.movilRepository = movilRepository;
	}
	
	

}

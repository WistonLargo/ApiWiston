package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.model.entities.Movil;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovilRepository extends JpaRepository<Movil, Long> {
	
	List<Movil>findTop5ByOrderByPuntuacionDesc();
}

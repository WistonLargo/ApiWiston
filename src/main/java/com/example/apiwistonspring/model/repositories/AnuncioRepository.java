package com.example.apiwistonspring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiwistonspring.model.entities.OldAnuncio;

public interface AnuncioRepository extends JpaRepository<OldAnuncio, Long> {

}

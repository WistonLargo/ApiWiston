package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.model.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
}

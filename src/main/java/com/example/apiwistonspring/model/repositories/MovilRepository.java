package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.model.entities.Movil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovilRepository extends JpaRepository<Movil, Long> {
}

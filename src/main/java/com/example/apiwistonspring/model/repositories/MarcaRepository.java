package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.model.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}

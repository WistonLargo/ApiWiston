package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.model.entities.Procesador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcesadorRepository extends JpaRepository<Procesador, Long> {
}

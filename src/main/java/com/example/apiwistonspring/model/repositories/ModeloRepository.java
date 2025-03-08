package com.example.apiwistonspring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apiwistonspring.model.entities.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
}

package com.example.apiwistonspring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantallaRepository extends JpaRepository<Modelo, Long> {
}

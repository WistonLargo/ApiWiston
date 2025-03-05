package com.example.apiwistonspring.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Modelo extends JpaRepository<Modelo, Long> {
}

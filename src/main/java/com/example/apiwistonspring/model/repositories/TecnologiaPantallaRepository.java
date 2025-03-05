package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaPantallaRepository extends JpaRepository<TecnologiaPantalla, Long> {
}

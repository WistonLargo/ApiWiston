package com.example.apiwistonspring.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiwistonspring.model.entities.ERole;
import com.example.apiwistonspring.model.entities.RoleUser;

public interface RoleRepository extends JpaRepository<RoleUser, Long> {

	public Optional<RoleUser> findByName(ERole erole);
}

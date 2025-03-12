package com.example.apiwistonspring.model.entities;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class AnuncioVenta extends Anuncio {
	@NonNull
	private double precio;
}

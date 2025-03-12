package com.example.apiwistonspring.model.entities;

import java.time.LocalDate;

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

	public AnuncioVenta(@NonNull UserEntity usuario, @NonNull Movil movil, @NonNull Estado estado,
			@NonNull LocalDate fechaPublicacion, @NonNull double precio) {
		super(usuario, movil, estado, fechaPublicacion);
		this.precio = precio;
	}
	
	
}

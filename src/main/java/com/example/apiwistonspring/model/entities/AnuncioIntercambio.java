package com.example.apiwistonspring.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioIntercambio extends Anuncio {
	@ManyToOne
	@JoinColumn(name = "movil_id")
	@NonNull
	Movil movilOfrecido;

}

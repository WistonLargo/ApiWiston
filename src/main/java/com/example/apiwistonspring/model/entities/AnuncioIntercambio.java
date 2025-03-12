package com.example.apiwistonspring.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
public class AnuncioIntercambio extends Anuncio {
	@ManyToOne
	@JoinColumn(name = "movil_id")
	@NonNull
	Movil movilOfrecido;

}

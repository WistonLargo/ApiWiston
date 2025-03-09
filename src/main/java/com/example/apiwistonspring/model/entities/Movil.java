package com.example.apiwistonspring.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Movil {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private int almacenamiento;
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Dimensiones dimensiones;
	@NonNull
	private LocalDate fechaLanzamiento;
	@NonNull
	private int megaPixeles;
	@NonNull
	private boolean nfc;
	@NonNull
	private double peso;
	@NonNull
	private double precio;
	@NonNull
	private int puntuacion;
	@NonNull
	private int ram;
	@NonNull
	private int numeroDeVisitas;
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Modelo modelo;
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Pantalla pantalla;
	@NonNull
	private Long referencia;
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Procesador procesador;

	public Long getMarcaId() {
		return modelo.getMarcaId();
	}
	
}

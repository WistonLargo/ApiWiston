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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	private int almacenamiento;
	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "dimensiones_id")
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
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "modelo_id")
	private Modelo modelo;
	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "pantalla_id")
	private Pantalla pantalla;
	@NonNull
	private Long referencia;
	@NonNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "procesador_id")
	private Procesador procesador;

	public Long getMarcaId() {
		return modelo.getMarcaId();
	}
	
}

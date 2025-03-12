package com.example.apiwistonspring.model.entities;

import java.time.LocalDate;

import com.example.apiwistonspring.enumeracion.EstadoTelefono;
import com.example.apiwistonspring.enumeracion.TipoCambio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Anuncio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "movil_id")
	private Movil movil;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@NonNull
	private UserEntity usuario;
	@NonNull
	private EstadoTelefono estado;
	@NonNull
	private TipoCambio tipoCambio;

	private LocalDate fechaPublicacion = LocalDate.now();
	@NonNull
	private boolean contestado;

	public Anuncio(Movil movil, @NonNull UserEntity usuario, @NonNull EstadoTelefono estado,
			@NonNull TipoCambio tipoCambio, @NonNull boolean contestado) {
		super();
		this.movil = movil;
		this.usuario = usuario;
		this.estado = estado;
		this.tipoCambio = tipoCambio;
		this.fechaPublicacion = LocalDate.now();
		this.contestado = contestado;
	}

}

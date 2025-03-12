package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.enumeracion.EstadoTelefono;
import com.example.apiwistonspring.enumeracion.TipoCambio;
import com.example.apiwistonspring.model.entities.Anuncio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

	List<Anuncio> findByEstadoAndTipoCambio(EstadoTelefono estado, TipoCambio cambio);
	List<Anuncio> findByEstado(EstadoTelefono estado);
	List<Anuncio> findByTipoCambio(TipoCambio cambio);


}

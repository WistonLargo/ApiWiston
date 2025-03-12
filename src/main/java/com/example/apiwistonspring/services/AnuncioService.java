package com.example.apiwistonspring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiwistonspring.dtos.AnuncioFilterDTO;
import com.example.apiwistonspring.model.entities.Anuncio;
import com.example.apiwistonspring.model.repositories.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;

	public List<Anuncio> getAllAnuncios() {
		return anuncioRepository.findAll();
	}

	public Optional<Anuncio> getAnuncioById(Long id) {
		return anuncioRepository.findById(id);
	}

	public Anuncio saveAnuncio(Anuncio anuncio) {
		return anuncioRepository.save(anuncio);
	}

	public Anuncio updateAnuncio(Long id, Anuncio anuncioDetails) {
		Optional<Anuncio> anuncio = anuncioRepository.findById(id);
		if (anuncio.isPresent()) {
			Anuncio updatedAnuncio = anuncio.get();
			updatedAnuncio.setEstado(anuncioDetails.getEstado());
			updatedAnuncio.setTipoCambio(anuncioDetails.getTipoCambio());
			return anuncioRepository.save(updatedAnuncio);
		}
		return null;
	}

	public void deleteAnuncio(Long id) {
		anuncioRepository.deleteById(id);
	}

	public List<Anuncio> getAnunciosFiltrados(AnuncioFilterDTO filtro) {
		if ((filtro.estado() != null) && (filtro.cambio() != null)) {
			return anuncioRepository.findByEstadoAndTipoCambio(filtro.estado(), filtro.cambio());
		}

		else if (filtro.estado() != null) {
			return anuncioRepository.findByEstado(filtro.estado());
		}

		else if (filtro.cambio() != null) {
			return anuncioRepository.findByTipoCambio(filtro.cambio());
		}

		return new ArrayList<>();
	}
}

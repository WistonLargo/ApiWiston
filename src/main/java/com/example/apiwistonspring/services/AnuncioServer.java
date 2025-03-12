package com.example.apiwistonspring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiwistonspring.model.entities.Anuncio;
import com.example.apiwistonspring.model.entities.AnuncioIntercambio;
import com.example.apiwistonspring.model.entities.AnuncioVenta;
import com.example.apiwistonspring.model.repositories.AnuncioRepository;

@Service
public class AnuncioServer implements InterfazAnuncioServer {
	@Autowired
	AnuncioRepository anuncioRepository;

	@Override
	public Anuncio saveAnuncio(Anuncio anuncio) {
		return anuncioRepository.save(anuncio);
	}

	@Override
	public List<Anuncio> getAllAnuncios() {
		return anuncioRepository.findAll();
	}

	@Override
	public List<AnuncioVenta> getAllAnunciosVenta() {
		return anuncioRepository.findAll().stream().filter(anuncio -> anuncio instanceof AnuncioVenta)
				.map(anuncio -> (AnuncioVenta) anuncio).collect(Collectors.toList());
	}

	@Override
	public List<AnuncioIntercambio> getAllAnunciosIntercambio() {
		return anuncioRepository.findAll().stream().filter(anuncio -> anuncio instanceof AnuncioIntercambio)
				.map(anuncio -> (AnuncioIntercambio) anuncio).collect(Collectors.toList());
	}
}

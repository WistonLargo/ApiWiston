package com.example.apiwistonspring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiwistonspring.model.entities.OldAnuncio;
import com.example.apiwistonspring.model.entities.OldAnuncioIntercambio;
import com.example.apiwistonspring.model.entities.OldAnuncioVenta;
import com.example.apiwistonspring.model.repositories.AnuncioRepository;

@Service
public class AnuncioServer implements InterfazAnuncioServer {
	@Autowired
	AnuncioRepository anuncioRepository;

	@Override
	public OldAnuncio saveAnuncio(OldAnuncio anuncio) {
		return anuncioRepository.save(anuncio);
	}

	@Override
	public List<OldAnuncio> getAllAnuncios() {
		return anuncioRepository.findAll();
	}

	@Override
	public List<OldAnuncioVenta> getAllAnunciosVenta() {
		return anuncioRepository.findAll().stream().filter(anuncio -> anuncio instanceof OldAnuncioVenta)
				.map(anuncio -> (OldAnuncioVenta) anuncio).collect(Collectors.toList());
	}

	@Override
	public List<OldAnuncioIntercambio> getAllAnunciosIntercambio() {
		return anuncioRepository.findAll().stream().filter(anuncio -> anuncio instanceof OldAnuncioIntercambio)
				.map(anuncio -> (OldAnuncioIntercambio) anuncio).collect(Collectors.toList());
	}
}

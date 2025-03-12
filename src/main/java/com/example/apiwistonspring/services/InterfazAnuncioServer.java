package com.example.apiwistonspring.services;

import java.util.List;

import com.example.apiwistonspring.model.entities.Anuncio;
import com.example.apiwistonspring.model.entities.AnuncioIntercambio;
import com.example.apiwistonspring.model.entities.AnuncioVenta;

public interface InterfazAnuncioServer {

	Anuncio saveAnuncio(Anuncio movil);

	List<Anuncio> getAllAnuncios();

	List<AnuncioVenta> getAllAnunciosVenta();

	List<AnuncioIntercambio> getAllAnunciosIntercambio();

}

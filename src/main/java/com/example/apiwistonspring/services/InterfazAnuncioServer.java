package com.example.apiwistonspring.services;

import java.util.List;

import com.example.apiwistonspring.model.entities.OldAnuncio;
import com.example.apiwistonspring.model.entities.OldAnuncioIntercambio;
import com.example.apiwistonspring.model.entities.OldAnuncioVenta;

public interface InterfazAnuncioServer {

	OldAnuncio saveAnuncio(OldAnuncio movil);

	List<OldAnuncio> getAllAnuncios();

	List<OldAnuncioVenta> getAllAnunciosVenta();

	List<OldAnuncioIntercambio> getAllAnunciosIntercambio();

}

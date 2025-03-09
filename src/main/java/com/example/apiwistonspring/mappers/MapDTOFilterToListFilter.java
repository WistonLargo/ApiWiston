package com.example.apiwistonspring.mappers;

import java.util.ArrayList;
import java.util.List;

import com.example.apiwistonspring.dtos.FilterDTO;
import com.example.apiwistonspring.filters.Filter;
import com.example.apiwistonspring.filters.FilterByAlmacenamiento;
import com.example.apiwistonspring.filters.FilterByMarca;
import com.example.apiwistonspring.filters.FilterByMegapixeles;
import com.example.apiwistonspring.filters.FilterByNfc;
import com.example.apiwistonspring.filters.FilterByPantallaSize;
import com.example.apiwistonspring.filters.FilterByPrecio;
import com.example.apiwistonspring.filters.FilterByProcesNombre;
import com.example.apiwistonspring.filters.FilterByPuntuacion;
import com.example.apiwistonspring.filters.FilterByRam;
import com.example.apiwistonspring.filters.FilterByTecPantalla;
import com.example.apiwistonspring.util.Intervalo;

public class MapDTOFilterToListFilter {
	public List<Filter<?>> map(FilterDTO dto) {

		List<Filter<?>> filtros = new ArrayList<>();

		// hay que agregar el que siempre es oblgatorio que es el de precio, los demás
		// se van añadiendo solo si están en el dto

		filtros.add(new FilterByPrecio(new Intervalo<>(dto.precioMin(), dto.precioMax())));

		if (dto.almacenamiento() != null) {
			filtros.add(new FilterByAlmacenamiento(dto.almacenamiento()));
		}

		if (dto.nombremarca() != null) {
			filtros.add(new FilterByMarca(dto.nombremarca()));
		}

		if (dto.megapixeles() != null) {
			filtros.add(new FilterByMegapixeles(dto.megapixeles()));
		}

		if (dto.nfc() != null) {
			filtros.add(new FilterByNfc(dto.nfc()));
		}

		if (dto.pantallaSize() != null) {
			filtros.add(new FilterByPantallaSize(dto.pantallaSize()));
		}

		if (dto.procesNombre() != null) {
			filtros.add(new FilterByProcesNombre(dto.procesNombre()));
		}

		if (dto.puntuacion() != null) {
			filtros.add(new FilterByPuntuacion(dto.puntuacion()));
		}

		if (dto.ramMax() != null && dto.ramMin() != null) {
			filtros.add(new FilterByRam(new Intervalo<>(dto.ramMin(), dto.ramMax())));
		}

		if (dto.tecPantalla() != null) {
			filtros.add(new FilterByTecPantalla(dto.tecPantalla()));
		}

		return filtros;
	}
}

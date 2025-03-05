package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByTecPantalla extends Filter<String> {

	public FilterByTecPantalla(String parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getPantalla().getTecnologiaPantalla().getTipo().equalsIgnoreCase(parameter);
	}

}

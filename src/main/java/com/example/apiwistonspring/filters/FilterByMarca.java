package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByMarca extends Filter<String> {

	public FilterByMarca(String parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getModelo().getMarca().getNombre().equalsIgnoreCase(parameter);
	}

}

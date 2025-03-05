package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByProcesNombre extends Filter<String> {

	public FilterByProcesNombre(String parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getProcesador().getNombre().equalsIgnoreCase(parameter);
	}

}

package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByPuntuacion extends Filter<Integer> {

	public FilterByPuntuacion(Integer parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getPuntuacion() == parameter;
	}

}

package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByPantallaSize extends Filter<Double> {

	public FilterByPantallaSize(Double parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getPantalla().getPantallaSize() == parameter;
	}

}

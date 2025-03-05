package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.util.Intervalo;

public class FilterByRam extends Filter<Intervalo<Integer>> {

	public FilterByRam(Intervalo<Integer> parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getRam() >= parameter.getMin() && item.getRam() <= parameter.getMax();
	}

}

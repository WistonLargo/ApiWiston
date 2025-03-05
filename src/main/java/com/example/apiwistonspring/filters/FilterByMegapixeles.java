package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByMegapixeles extends Filter<Integer> {

	public FilterByMegapixeles(Integer parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getMegaPixeles() == parameter;
	}

}

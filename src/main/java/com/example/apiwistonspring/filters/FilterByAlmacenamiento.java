package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByAlmacenamiento extends Filter<Integer>{

	public FilterByAlmacenamiento(Integer parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.getAlmacenamiento() == parameter;
	}

	

}

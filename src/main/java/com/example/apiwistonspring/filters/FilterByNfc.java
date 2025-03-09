package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class FilterByNfc extends Filter<Boolean>{

	public FilterByNfc(Boolean parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.isNfc()==parameter;
	}

}

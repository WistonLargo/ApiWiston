package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public class filterByNfc extends Filter<Boolean>{

	public filterByNfc(Boolean parameter) {
		super(parameter);
	}

	@Override
	public boolean filter(Movil item) {
		return item.isNfc()==parameter;
	}

}

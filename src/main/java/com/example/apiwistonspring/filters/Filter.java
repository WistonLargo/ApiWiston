package com.example.apiwistonspring.filters;

import com.example.apiwistonspring.model.entities.Movil;

public abstract class Filter <T>{
	protected T parameter; 
	
	public Filter(T parameter) {
		this.parameter = parameter;
	}
	
	public abstract boolean filter(Movil item); 

}

package com.example.apiwistonspring.model.services;

public interface FilterService<ToFilter,Parametros>{
	public ToFilter filter(Parametros parametros);
}

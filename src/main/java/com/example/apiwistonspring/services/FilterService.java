package com.example.apiwistonspring.services;

public interface FilterService<ToFilter,Parametros>{
	public ToFilter filter(Parametros parametros);
}

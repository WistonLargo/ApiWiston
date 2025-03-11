package com.example.apiwistonspring.mappers;

public interface MyMapper<T,S> {
	public S map(T t);
}

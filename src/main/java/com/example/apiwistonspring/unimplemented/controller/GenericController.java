package com.example.apiwistonspring.unimplemented.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface GenericController<T> {
	public ResponseEntity<List<T>> get();
    public ResponseEntity<T> post(T t);
    public ResponseEntity<T> put(T t);
    public ResponseEntity<T> delete(Long id);

}

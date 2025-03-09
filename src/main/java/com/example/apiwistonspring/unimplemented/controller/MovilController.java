package com.example.apiwistonspring.unimplemented.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.apiwistonspring.dtos.FilterDTO;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.services.MovilService;

public class MovilController implements GenericController<Movil> {
	MovilService movilService;

	@Override
	public ResponseEntity<List<Movil>> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Movil> post(Movil t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Movil> put(Movil t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Movil> delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("filter")
	public ResponseEntity<List<Movil>> getMethodName(@RequestBody FilterDTO movilFilter) {
		return ResponseEntity.ok(movilService.filterMoviles(movilFilter));
	}

}

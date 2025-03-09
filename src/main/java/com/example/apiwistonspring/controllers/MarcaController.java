package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.repositories.DimensionesRepository;
import com.example.apiwistonspring.model.repositories.MarcaRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

public class MarcaController implements GenericController<Marca> {

	@Autowired
    private MarcaRepository marcaRepository;


	@GetMapping("/apiWiston/Marca")
    @ResponseBody
    @Override
	public ResponseEntity<List<Marca>> get() {
		return ResponseEntity.ok(marcaRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Marca")
	@Override
	public ResponseEntity<Marca> post(@RequestBody Marca marca) {
		if (marcaRepository.existsById(marca.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(marcaRepository.save(marca));
    }

	@PutMapping("/apiWiston/Marca")
	@Override
	public ResponseEntity<Marca> put(@RequestBody Marca marca) {
		if (marcaRepository.existsById(marca.getId())) {
			marcaRepository.save(marca);
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Marca/{id}")
	@Override
	public ResponseEntity<Marca> delete(@PathVariable long id) {
		 if (marcaRepository.existsById(id)) {
			 marcaRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


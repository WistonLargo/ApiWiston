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

import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;
import com.example.apiwistonspring.model.repositories.PantallaRepository;
import com.example.apiwistonspring.model.repositories.ProcesadorRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

public class ProcesadorController implements GenericController<Procesador> {

	@Autowired
    private ProcesadorRepository procesadorRepository;


	@GetMapping("/apiWiston/Pantalla")
    @ResponseBody
    @Override
	public ResponseEntity<List<Procesador>> get() {
		return ResponseEntity.ok(procesadorRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Pantalla")
	@Override
	public ResponseEntity<Procesador> post(@RequestBody Procesador procesador) {
		if (procesadorRepository.existsById(procesador.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(procesadorRepository.save(procesador));
    }

	@PutMapping("/apiWiston/Pantalla")
	@Override
	public ResponseEntity<Procesador> put(@RequestBody Procesador procesador) {
		if (procesadorRepository.existsById(procesador.getId())) {
			procesadorRepository.save(procesador);
            return ResponseEntity.ok(procesador);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Pantalla/{id}")
	@Override
	public ResponseEntity<Procesador> delete(@PathVariable long id) {
		 if (procesadorRepository.existsById(id)) {
			 procesadorRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

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

import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.repositories.ModeloRepository;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

public class MovilController implements GenericController<Movil> {
	@Autowired
    private MovilRepository movilRepository;


	@GetMapping("/apiWiston/Movil")
    @ResponseBody
    @Override
	public ResponseEntity<List<Movil>> get() {
		return ResponseEntity.ok(movilRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Movil")
	@Override
	public ResponseEntity<Movil> post(@RequestBody Movil movil) {
		if (movilRepository.existsById(movil.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(movilRepository.save(movil));
    }

	@PutMapping("/apiWiston/Movil")
	@Override
	public ResponseEntity<Movil> put(@RequestBody Movil movil) {
		if (movilRepository.existsById(movil.getId())) {
			movilRepository.save(movil);
            return ResponseEntity.ok(movil);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Movil/{id}")
	@Override
	public ResponseEntity<Movil> delete(@PathVariable long id) {
		 if (movilRepository.existsById(id)) {
			 movilRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

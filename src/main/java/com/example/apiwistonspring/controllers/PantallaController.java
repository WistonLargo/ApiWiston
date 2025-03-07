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

import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.model.repositories.PantallaRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

public class PantallaController implements GenericController<Pantalla> {
	
	@Autowired
    private PantallaRepository pantallaRepository;


	@GetMapping("/apiWiston/Pantalla")
    @ResponseBody
    @Override
	public ResponseEntity<List<Pantalla>> get() {
		return ResponseEntity.ok(pantallaRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Pantalla")
	@Override
	public ResponseEntity<Pantalla> post(@RequestBody Pantalla pantalla) {
		if (pantallaRepository.existsById(pantalla.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(pantallaRepository.save(pantalla));
    }

	@PutMapping("/apiWiston/Pantalla")
	@Override
	public ResponseEntity<Pantalla> put(@RequestBody Pantalla pantalla) {
		if (pantallaRepository.existsById(pantalla.getId())) {
			pantallaRepository.save(pantalla);
            return ResponseEntity.ok(pantalla);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Pantalla/{id}")
	@Override
	public ResponseEntity<Pantalla> delete(@PathVariable long id) {
		 if (pantallaRepository.existsById(id)) {
			 pantallaRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
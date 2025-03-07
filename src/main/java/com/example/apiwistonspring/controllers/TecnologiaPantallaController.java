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

import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import com.example.apiwistonspring.model.repositories.TecnologiaPantallaRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

public class TecnologiaPantallaController implements GenericController<TecnologiaPantalla> {
	@Autowired
    private TecnologiaPantallaRepository tecnologiaPantallaRepository;


	@GetMapping("/apiWiston/Pantalla")
    @ResponseBody
    @Override
	public ResponseEntity<List<TecnologiaPantalla>> get() {
		return ResponseEntity.ok(tecnologiaPantallaRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Pantalla")
	@Override
	public ResponseEntity<TecnologiaPantalla> post(@RequestBody TecnologiaPantalla tecnologiaPantalla) {
		if (tecnologiaPantallaRepository.existsById(tecnologiaPantalla.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tecnologiaPantallaRepository.save(tecnologiaPantalla));
    }

	@PutMapping("/apiWiston/Pantalla")
	@Override
	public ResponseEntity<TecnologiaPantalla> put(@RequestBody TecnologiaPantalla tecnologiaPantalla) {
		if (tecnologiaPantallaRepository.existsById(tecnologiaPantalla.getId())) {
			tecnologiaPantallaRepository.save(tecnologiaPantalla);
            return ResponseEntity.ok(tecnologiaPantalla);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Pantalla/{id}")
	@Override
	public ResponseEntity<TecnologiaPantalla> delete(@PathVariable long id) {
		 if (tecnologiaPantallaRepository.existsById(id)) {
			 tecnologiaPantallaRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

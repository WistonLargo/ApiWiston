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
import com.example.apiwistonspring.model.repositories.ModeloRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

public class ModeloController implements GenericController<Modelo> {
	@Autowired
    private ModeloRepository modeloRepository;


	@GetMapping("/apiWiston/Modelo")
    @ResponseBody
    @Override
	public ResponseEntity<List<Modelo>> get() {
		return ResponseEntity.ok(modeloRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Modelo")
	@Override
	public ResponseEntity<Modelo> post(@RequestBody Modelo modelo) {
		if (modeloRepository.existsById(modelo.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(modeloRepository.save(modelo));
    }

	@PutMapping("/apiWiston/Modelo")
	@Override
	public ResponseEntity<Modelo> put(@RequestBody Modelo modelo) {
		if (modeloRepository.existsById(modelo.getId())) {
			modeloRepository.save(modelo);
            return ResponseEntity.ok(modelo);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Modelo/{id}")
	@Override
	public ResponseEntity<Modelo> delete(@PathVariable long id) {
		 if (modeloRepository.existsById(id)) {
			 modeloRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

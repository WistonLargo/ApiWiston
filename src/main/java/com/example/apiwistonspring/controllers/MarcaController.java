package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.repositories.DimensionesRepository;
import com.example.apiwistonspring.model.repositories.MarcaRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@RestController
@RequestMapping("/marca")
public class MarcaController implements GenericController<Marca> {

	@Autowired
    private MarcaRepository marcaRepository;


	@GetMapping("/get")
    @ResponseBody
    @Override
	public ResponseEntity<List<Marca>> get() {
		return ResponseEntity.ok(marcaRepository.findAll());
	}
	
	@PostMapping("/post")
	@Override
	public ResponseEntity<Marca> post(@RequestBody Marca marca) {
		if (marcaRepository.existsById(marca.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(marcaRepository.save(marca));
    }

	@PutMapping("/put")
	@Override
	public ResponseEntity<Marca> put(@RequestBody Marca marca) {
		if (marcaRepository.existsById(marca.getId())) {
			marcaRepository.save(marca);
            return ResponseEntity.ok(marca);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<Marca> delete(@RequestParam Long id) {
		 if (marcaRepository.existsById(id)) {
			 marcaRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


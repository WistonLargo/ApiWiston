package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;
import com.example.apiwistonspring.model.repositories.PantallaRepository;
import com.example.apiwistonspring.model.repositories.ProcesadorRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@RestController
@RequestMapping("/procesador")
public class ProcesadorController implements GenericController<Procesador> {

	@Autowired
    private ProcesadorRepository procesadorRepository;


	@GetMapping("/get")
    @ResponseBody
    @Override
	public ResponseEntity<List<Procesador>> get() {
		return ResponseEntity.ok(procesadorRepository.findAll());
	}
	
	@PostMapping("/post")
	@Override
	public ResponseEntity<Procesador> post(@RequestBody Procesador procesador) {
		if (procesadorRepository.existsById(procesador.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(procesadorRepository.save(procesador));
    }

	@PutMapping("/put")
	@Override
	public ResponseEntity<Procesador> put(@RequestBody Procesador procesador) {
		if (procesadorRepository.existsById(procesador.getId())) {
			procesadorRepository.save(procesador);
            return ResponseEntity.ok(procesador);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<Procesador> delete(@RequestParam Long id) {
		 if (procesadorRepository.existsById(id)) {
			 procesadorRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

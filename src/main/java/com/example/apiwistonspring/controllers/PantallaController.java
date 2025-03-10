package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.model.repositories.PantallaRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@RestController
@RequestMapping("/pantalla")
public class PantallaController implements GenericController<Pantalla> {
	
	@Autowired
    private PantallaRepository pantallaRepository;


	@GetMapping("/get")
    @ResponseBody
    @Override
	public ResponseEntity<List<Pantalla>> get() {
		return ResponseEntity.ok(pantallaRepository.findAll());
	}
	
	@PostMapping("/post")
	@Override
	public ResponseEntity<Pantalla> post(@RequestBody Pantalla pantalla) {
		if (pantallaRepository.existsById(pantalla.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(pantallaRepository.save(pantalla));
    }

	@PutMapping("/put")
	@Override
	public ResponseEntity<Pantalla> put(@RequestBody Pantalla pantalla) {
		if (pantallaRepository.existsById(pantalla.getId())) {
			pantallaRepository.save(pantalla);
            return ResponseEntity.ok(pantalla);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<Pantalla> delete(@RequestParam Long id) {
		 if (pantallaRepository.existsById(id)) {
			 pantallaRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
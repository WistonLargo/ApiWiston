package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.repositories.DimensionesRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@RestController
@RequestMapping("/dimensiones")
public class DimensionesController implements GenericController<Dimensiones> {
	
	@Autowired
    private DimensionesRepository dimensionesRepository;


	@GetMapping("/getAll")
    @ResponseBody
    @Override
	public ResponseEntity<List<Dimensiones>> get() {
		return ResponseEntity.ok(dimensionesRepository.findAll());
	}
	
	@PostMapping("/post")
	@Override
	public ResponseEntity<Dimensiones> post(@RequestBody Dimensiones dimensiones) {
		if (dimensionesRepository.existsById(dimensiones.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dimensionesRepository.save(dimensiones));
    }

	@PutMapping("/put")
	@Override
	public ResponseEntity<Dimensiones> put(@RequestBody Dimensiones dimensiones) {
		if (dimensionesRepository.existsById(dimensiones.getId())) {
			dimensionesRepository.save(dimensiones);
            return ResponseEntity.ok(dimensiones);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<Dimensiones> delete(@RequestParam Long id) {
		 if (dimensionesRepository.existsById(id)) {
			 dimensionesRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
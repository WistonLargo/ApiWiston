package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.repositories.DimensionesRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class DimensionesController implements GenericController<Dimensiones> {
	
	@Autowired
    private DimensionesRepository dimensionesRepository;


	@GetMapping("/apiWiston/Dimensiones")
    @ResponseBody
    @Override
	public ResponseEntity<List<Dimensiones>> get() {
		return ResponseEntity.ok(dimensionesRepository.findAll());
	}
	
	@PostMapping("/apiWiston/Dimensiones")
	@Override
	public ResponseEntity<Dimensiones> post(@RequestBody Dimensiones dimensiones) {
		if (dimensionesRepository.existsById(dimensiones.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dimensionesRepository.save(dimensiones));
    }

	@PutMapping("/apiWiston/Dimensiones")
	@Override
	public ResponseEntity<Dimensiones> put(@RequestBody Dimensiones dimensiones) {
		if (dimensionesRepository.existsById(dimensiones.getId())) {
			dimensionesRepository.save(dimensiones);
            return ResponseEntity.ok(dimensiones);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/apiWiston/Dimensiones/{id}")
	@Override
	public ResponseEntity<Dimensiones> delete(@PathVariable long id) {
		 if (dimensionesRepository.existsById(id)) {
			 dimensionesRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
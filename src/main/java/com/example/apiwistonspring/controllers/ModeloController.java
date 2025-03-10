package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.repositories.ModeloRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@RestController
@RequestMapping("/modelo")
public class ModeloController implements GenericController<Modelo> {
	@Autowired
    private ModeloRepository modeloRepository;


	@GetMapping("/get")
    @ResponseBody
    @Override
	public ResponseEntity<List<Modelo>> get() {
		return ResponseEntity.ok(modeloRepository.findAll());
	}
	
	@PostMapping("/post")
	@Override
	public ResponseEntity<Modelo> post(@RequestBody Modelo modelo) {
		if (modeloRepository.existsById(modelo.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(modeloRepository.save(modelo));
    }

	@PutMapping("/put")
	@Override
	public ResponseEntity<Modelo> put(@RequestBody Modelo modelo) {
		if (modeloRepository.existsById(modelo.getId())) {
			modeloRepository.save(modelo);
            return ResponseEntity.ok(modelo);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<Modelo> delete(@RequestParam Long id) {
		 if (modeloRepository.existsById(id)) {
			 modeloRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

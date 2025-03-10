package com.example.apiwistonspring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import com.example.apiwistonspring.model.repositories.TecnologiaPantallaRepository;
import com.example.apiwistonspring.unimplemented.controller.GenericController;

@RestController
@RequestMapping("/tecnologia-pantalla")
public class TecnologiaPantallaController implements GenericController<TecnologiaPantalla> {
	@Autowired
    private TecnologiaPantallaRepository tecnologiaPantallaRepository;


	@GetMapping("/get")
    @ResponseBody
    @Override
	public ResponseEntity<List<TecnologiaPantalla>> get() {
		return ResponseEntity.ok(tecnologiaPantallaRepository.findAll());
	}
	
	@PostMapping("/post")
	@Override
	public ResponseEntity<TecnologiaPantalla> post(@RequestBody TecnologiaPantalla tecnologiaPantalla) {
		if (tecnologiaPantallaRepository.existsById(tecnologiaPantalla.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tecnologiaPantallaRepository.save(tecnologiaPantalla));
    }

	@PutMapping("/put")
	@Override
	public ResponseEntity<TecnologiaPantalla> put(@RequestBody TecnologiaPantalla tecnologiaPantalla) {
		if (tecnologiaPantallaRepository.existsById(tecnologiaPantalla.getId())) {
			tecnologiaPantallaRepository.save(tecnologiaPantalla);
            return ResponseEntity.ok(tecnologiaPantalla);
        }
        return ResponseEntity.notFound().build();
    }

	@DeleteMapping("/delete")
	@Override
	public ResponseEntity<TecnologiaPantalla> delete(@RequestParam Long id) {
		 if (tecnologiaPantallaRepository.existsById(id)) {
			 tecnologiaPantallaRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

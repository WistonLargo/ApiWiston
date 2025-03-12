package com.example.apiwistonspring.controllers;

import com.example.apiwistonspring.dtos.AnuncioFilterDTO;
import com.example.apiwistonspring.enumeracion.EstadoTelefono;
import com.example.apiwistonspring.enumeracion.TipoCambio;
import com.example.apiwistonspring.model.entities.Anuncio;
import com.example.apiwistonspring.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioService anuncioService;

	@GetMapping
	public List<Anuncio> getAllAnuncios() {
		return anuncioService.getAllAnuncios();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Anuncio> getAnuncioById(@PathVariable Long id) {
		return anuncioService.getAnuncioById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Anuncio> createAnuncio(@RequestBody Anuncio anuncio) {
		return ResponseEntity.status(HttpStatus.CREATED).body(anuncioService.saveAnuncio(anuncio));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Anuncio> updateAnuncio(@PathVariable Long id, @RequestBody Anuncio anuncioDetails) {
		Anuncio updatedAnuncio = anuncioService.updateAnuncio(id, anuncioDetails);
		if (updatedAnuncio != null) {
			return ResponseEntity.ok(updatedAnuncio);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAnuncio(@PathVariable Long id) {
		anuncioService.deleteAnuncio(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/filtrados")
	public ResponseEntity<List<Anuncio>> getAnunciosFiltrados(@RequestParam EstadoTelefono estado,
			@RequestParam TipoCambio cambio) {

		AnuncioFilterDTO filtro = new AnuncioFilterDTO(estado, cambio);
		List<Anuncio> anuncios = anuncioService.getAnunciosFiltrados(filtro);

		if (anuncios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(anuncios);
	}
}

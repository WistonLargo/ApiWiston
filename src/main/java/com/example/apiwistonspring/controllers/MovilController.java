package com.example.apiwistonspring.controllers;

import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.services.MovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moviles")
public class MovilController {

    @Autowired
    private MovilService movilService;

    @GetMapping
    public List<Movil> getAllMoviles() {
        return movilService.getAllMoviles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movil> getMovilById(@PathVariable Long id) {
        Optional<Movil> movil = movilService.getMovilById(id);
        return movil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movil> saveMovil(@RequestBody Movil movil) {
        Movil savedMovil = movilService.saveMovil(movil);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movil> updateMovil(@PathVariable Long id, @RequestBody Movil movil) {
        try {
            Movil updatedMovil = movilService.updateMovil(id, movil);
            return ResponseEntity.ok(updatedMovil);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovil(@PathVariable Long id) {
        try {
            movilService.deleteMovil(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

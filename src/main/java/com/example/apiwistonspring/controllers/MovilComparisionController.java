package com.example.apiwistonspring.controllers;

import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.services.ComparisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/movil-comparision")
public class MovilComparisionController {

    @Autowired
    private final ComparisionService comparisionService;

    public MovilComparisionController(ComparisionService comparisionService) {
        this.comparisionService = comparisionService;
    }

    public ResponseEntity<List<MovilComparisionDTO>> compareTwoMoviles(@RequestParam Long idMovil1,@RequestParam Long idMovil2) {
        return ResponseEntity.ok(comparisionService.compareTwoMoviles(idMovil1, idMovil2));
    }
}

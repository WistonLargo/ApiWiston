package com.example.apiwistonspring.services;

import com.example.apiwistonspring.model.entities.Movil;

import java.util.List;
import java.util.Optional;

public interface InterfazMovilService {

    //Crear un nuevo movil
    Movil saveMovil(Movil movil);

    //Obtener todos los moviles
    List<Movil> getAllMoviles();

    //Obtener un movil por su id
    Optional<Movil> getMovilById(Long id);

    //Actualizar un movil
    Movil updateMovil(Long id, Movil updatedMovil);

    //Eliminar un movil
    void deleteMovil(Long id);
}

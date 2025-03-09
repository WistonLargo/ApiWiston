package com.example.apiwistonspring.dtos;

import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.entities.Modelo;

public record MovilDTO(Modelo modelo, Marca marca, int numeroNucleos, int ram, int almacenamiento, double precio) {
}

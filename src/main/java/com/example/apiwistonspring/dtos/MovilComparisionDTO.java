package com.example.apiwistonspring.dtos;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;

public record MovilComparisionDTO(int almacenamiento, Dimensiones dimensiones, int megaPixeles, boolean nfc, double peso, double precio, int puntuacion, int ram, Modelo modelo, Pantalla pantalla, Procesador procesador) {
}

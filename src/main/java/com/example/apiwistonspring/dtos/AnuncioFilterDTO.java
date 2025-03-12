package com.example.apiwistonspring.dtos;

import com.example.apiwistonspring.enumeracion.EstadoTelefono;
import com.example.apiwistonspring.enumeracion.TipoCambio;

public record AnuncioFilterDTO(EstadoTelefono estado, TipoCambio cambio) {

}

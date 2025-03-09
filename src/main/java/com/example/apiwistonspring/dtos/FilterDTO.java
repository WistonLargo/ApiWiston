package com.example.apiwistonspring.dtos;

import jakarta.annotation.Nonnull;

public record FilterDTO(
		//hay que añadir el filtro de precio y ponerlo como not null ya que es el filtro obligatorio

		@Nonnull Double precioMin,
		@Nonnull Double precioMax,
		Integer almacenamiento, String nombremarca, Integer megapixeles, Boolean nfc, Double pantallaSize,
		String procesNombre, Integer puntuacion, Integer ramMin, Integer ramMax, String tecPantalla)

{

}

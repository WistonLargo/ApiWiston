package com.example.apiwistonspring.filters;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.apiwistonspring.dtos.FilterDTO;
import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;
import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.services.MovilService;

class FilterTest {

	@Mock
	private MovilRepository movilRepository;

	@InjectMocks
	private MovilService movilService;

	private Movil movil1;
	private Movil movil2;
	private Movil movil3;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Móvil1
		Marca marca1 = new Marca("Samsung");
		Modelo modelo1 = new Modelo(marca1, 123456L, "Samsung Galaxy S21");
		TecnologiaPantalla tecnologiaPantalla1 = new TecnologiaPantalla(101L, "AMOLED");
		Pantalla pantalla1 = new Pantalla(tecnologiaPantalla1, 6.2);
		Procesador procesador1 = new Procesador("Snapdragon 888", 123456L, 8, 2.84);
		Dimensiones dimensiones1 = new Dimensiones(15.0, 7.0, 0.8);
		movil1 = new Movil(128, dimensiones1, LocalDate.now(), 12, true, 200, 599.99, 4, 8, 0, modelo1, pantalla1,
				123456L, procesador1);

		// Móvil2
		Marca marca2 = new Marca("Apple");
		Modelo modelo2 = new Modelo(marca2, 123457L, "iPhone 13");
		TecnologiaPantalla tecnologiaPantalla2 = new TecnologiaPantalla(102L, "OLED");
		Pantalla pantalla2 = new Pantalla(tecnologiaPantalla2, 6.5);
		Procesador procesador2 = new Procesador("A15 Bionic", 123457L, 6, 3.2);
		Dimensiones dimensiones2 = new Dimensiones(16.0, 8.0, 0.9);
		movil2 = new Movil(256, dimensiones2, LocalDate.now(), 10, false, 220, 799.99, 6, 8, 0, modelo2, pantalla2,
				123457L, procesador2);

		// Móvil3
		Marca marca3 = new Marca("Xiaomi");
		Modelo modelo3 = new Modelo(marca3, 123458L, "Xiaomi Mi 11");
		TecnologiaPantalla tecnologiaPantalla3 = new TecnologiaPantalla(103L, "AMOLED");
		Pantalla pantalla3 = new Pantalla(tecnologiaPantalla3, 6.7);
		Procesador procesador3 = new Procesador("Snapdragon 888", 123458L, 8, 2.84);
		Dimensiones dimensiones3 = new Dimensiones(15.5, 7.2, 0.9);
		movil3 = new Movil(128, dimensiones3, LocalDate.now(), 12, true, 210, 699.99, 4, 8, 0, modelo3, pantalla3,
				123458L, procesador3);

	}

	@Test
	void filterMovilesByAlmacenamiento() {
		// Creamos el filtro que busca por almacenamiento
		FilterDTO filterDTO = new FilterDTO(128, null, null, null, null, null, null, null, null, null);

	}

	@Test
	void filterMovilesByMarca() {
		FilterDTO filterDTO = new FilterDTO(null, "Apple", null, null, null, null, null, null, null, null);

	}

	@Test
	void filterMovilesMultipleFilters() {
		FilterDTO filterDTO = new FilterDTO(128, "Samsung", null, null, null, null, null, null, null, null);

	}
}

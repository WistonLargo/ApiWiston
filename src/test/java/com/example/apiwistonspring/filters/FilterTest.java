package com.example.apiwistonspring.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

@SpringBootTest
@Transactional
class FilterTest {
	@Autowired
	private MovilRepository movilRepository;

	@Autowired
	private MovilService movilService;

	@Test
	void filterMovilesByAlmacenamiento() {
		// Creamos el filtro que busca por almacenamiento
		FilterDTO filterDTO = new FilterDTO(0.10, 1000.00, 128, null, null, null, null, null, null, null, null, null);
		List<Movil> filtrados = movilService.filterMoviles(filterDTO);
		assertEquals(3, filtrados.size());

	}

	@Test
	void filterMovilesByMarca() {
		FilterDTO filterDTO = new FilterDTO(10.00, 900.00, null, "Apple", null, null, null, null, null, null, null,
				null);
		List<Movil> filtradoMarca = movilService.filterMoviles(filterDTO);
		assertEquals(3, filtradoMarca.size());

		// "apple" y almacenamiento 64
		FilterDTO filterDTO2 = new FilterDTO(10.00, 900.00, 64, "Apple", null, null, null, null, null, null, null,
				null);
		List<Movil> filtradoMarca2 = movilService.filterMoviles(filterDTO2);
		assertEquals(2, filtradoMarca2.size());

	}

	@Test
	void filterMovilesMultipleFilters() {
		FilterDTO filterDTO = new FilterDTO(10.00, 900.00, 128, "Samsung", null, true, null, null, null, null, null,
				null);
		List<Movil> filtradoMulti = movilService.filterMoviles(filterDTO);
		assertEquals(2, filtradoMulti.size());

		// probando también nfc
		FilterDTO filterDTO2 = new FilterDTO(100.00, 800.00, 128, "samsung", null, true, null, null, null, null, null,
				null);
		List<Movil> filtradoMulti2 = movilService.filterMoviles(filterDTO2);
		assertEquals(1, filtradoMulti2.size());

		FilterDTO filterDTO3 = new FilterDTO(10.00, 50.00, 128, "Samsung", null, true, null, null, null, null, null,
				null);
		List<Movil> filtradoMulti3 = movilService.filterMoviles(filterDTO3);
		assertEquals(0, filtradoMulti3.size());

	}

	@Test
	void filtrarPorPrecio() {
		FilterDTO filterDTO = new FilterDTO(100.00, 400.00, null, null, null, null, null, null, null, null, null, null);
		List<Movil> filtradoprecio = movilService.filterMoviles(filterDTO);
		assertEquals(2, filtradoprecio.size());
	}

	@Test
	void probandoMasFiltros() {
		//probando todos los filtros a la vez 
		FilterDTO filter = new FilterDTO(10.0, 900.0, 128, "Samsung", 108, true, 6.5, "Snapdragon 888", 90, 1, 9,
				"AMOLED");
		List<Movil> filtraList = movilService.filterMoviles(filter);
		assertEquals(1, filtraList.size());
	}
}

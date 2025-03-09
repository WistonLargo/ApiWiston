package com.example.apiwistonspring.test;


import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;
import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class MovilRepositoryTest {

	@Autowired
	private MovilRepository movilRepository;

	@Test
	public void testFindTop5ByOrderByNumeroDeVisitasDesc() {

		Dimensiones dimensiones = new Dimensiones(150.0, 70.0, 8.0); // Ejemplo de dimensiones (alto, ancho, grosor)
		Marca marca = new Marca("marca");
		TecnologiaPantalla tecnologiaPantalla = new TecnologiaPantalla(1L,"Amoled");
		Modelo modelo = new Modelo(marca,1L,"modelo");
		Pantalla pantalla = new Pantalla(tecnologiaPantalla,6.5);
		Procesador procesador = new Procesador("Snapdragon 888", 8L,4,3.1);

		// Insertar datos de prueba
		Movil movil1 = new Movil(128, dimensiones, LocalDate.of(2022, 1, 1), 50, true, 200.0, 899.99, 90, 8, 150,
				modelo, pantalla, 10001L, procesador);
		Movil movil2 = new Movil(64, dimensiones, LocalDate.of(2021, 6, 10), 48, true, 180.0, 599.99, 80, 6, 200,
				modelo, pantalla, 10002L, procesador);
		Movil movil3 = new Movil(256, dimensiones, LocalDate.of(2023, 3, 15), 108, true, 220.0, 1199.99, 95, 12, 50,
				modelo, pantalla, 10003L, procesador);
		Movil movil4 = new Movil(32, dimensiones, LocalDate.of(2020, 8, 5), 24, false, 150.0, 299.99, 70, 4, 300,
				modelo, pantalla, 10004L, procesador);
		Movil movil5 = new Movil(512, dimensiones, LocalDate.of(2022, 11, 25), 200, true, 240.0, 1499.99, 100, 16, 250,
				modelo, pantalla, 10005L, procesador);
		Movil movil6 = new Movil(128, dimensiones, LocalDate.of(2021, 12, 30), 64, true, 210.0, 749.99, 85, 8, 400,
				modelo, pantalla, 10006L, procesador);

		movilRepository.save(movil1);
		movilRepository.save(movil2);
		movilRepository.save(movil3);
		movilRepository.save(movil4);
		movilRepository.save(movil5);
		movilRepository.save(movil6);

		// Ejecutar el método que se quiere probar
		List<Movil> top5Moviles = movilRepository.findTop5ByOrderByNumeroDeVisitasDesc();

		// Verificar que solo hay 5 resultados
		assertEquals(5,top5Moviles.size());

		// Verificar que los resultados están en orden descendente por número de visitas
		assertEquals(400,top5Moviles.get(0).getNumeroDeVisitas());
		assertEquals(300,top5Moviles.get(1).getNumeroDeVisitas());
		assertEquals(250,top5Moviles.get(2).getNumeroDeVisitas());
		assertEquals(200,top5Moviles.get(3).getNumeroDeVisitas());
		assertEquals(150,top5Moviles.get(4).getNumeroDeVisitas());
	}
}

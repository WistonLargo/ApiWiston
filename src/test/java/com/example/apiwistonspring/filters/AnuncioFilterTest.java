package com.example.apiwistonspring.filters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.apiwistonspring.dtos.AnuncioFilterDTO;
import com.example.apiwistonspring.enumeracion.EstadoTelefono;
import com.example.apiwistonspring.enumeracion.TipoCambio;
import com.example.apiwistonspring.model.entities.Anuncio;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.services.AnuncioService;
import com.example.apiwistonspring.services.MovilService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class AnuncioFilterTest {
	@Autowired
	private MovilRepository movilRepository;
	@Autowired
	private AnuncioService anuncioService;

	@Test
	void test() {
		AnuncioFilterDTO dto = new AnuncioFilterDTO(EstadoTelefono.experimentado, null);
		List<Anuncio> filtrados = anuncioService.getAnunciosFiltrados(dto);
		assertEquals(1, filtrados.size());
		
		AnuncioFilterDTO dto1 = new AnuncioFilterDTO(EstadoTelefono.heroedeguerra, TipoCambio.venta);
		List<Anuncio> filtrados2 = anuncioService.getAnunciosFiltrados(dto1);
		assertEquals(1, filtrados2.size());
		
		AnuncioFilterDTO dto3 = new AnuncioFilterDTO(null, null);
		List<Anuncio> filtrados3 = anuncioService.getAnunciosFiltrados(dto3);
		assertEquals(0, filtrados3.size());
		
		AnuncioFilterDTO dto4 = new AnuncioFilterDTO(EstadoTelefono.superviviente, TipoCambio.intercambio);
		List<Anuncio> filtrados4 = anuncioService.getAnunciosFiltrados(dto4);
		assertEquals(1, filtrados4.size());
	}

}

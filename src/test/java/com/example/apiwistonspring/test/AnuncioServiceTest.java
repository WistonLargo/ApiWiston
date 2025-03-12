package com.example.apiwistonspring.test;

import com.example.apiwistonspring.model.entities.*;
import com.example.apiwistonspring.model.repositories.AnuncioRepository;
import com.example.apiwistonspring.services.AnuncioService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnuncioServiceTest {

    @Mock
    private AnuncioRepository anuncioRepository;

    @InjectMocks
    private AnuncioService anuncioService;

    private Movil movil;
    private Anuncio anuncio;

    @BeforeEach
    void setUp() {
        Marca marca = new Marca("Samsung");
        Modelo modelo = new Modelo(marca, 123456.4, "Samsung Galaxy S21");
        TecnologiaPantalla tecnologiaPantalla = new TecnologiaPantalla(101.5, "AMOLED");
        Pantalla pantalla = new Pantalla(tecnologiaPantalla, 6.2);
        Procesador procesador = new Procesador("Snapdragon 888", 123456L, 8, 2.84);
        Dimensiones dimensiones = new Dimensiones(15.0, 7.0, 0.8);

        movil = new Movil(128, dimensiones, LocalDate.now(), 12, true, 200, 599.99, 4, 4, 8,
                modelo, pantalla, 123456L, procesador);
        movil.setId(1L);

        anuncio = new Anuncio(movil, "Intacto", "Venta");
    }

    @Test
    void saveAnuncio() {
        when(anuncioRepository.save(anuncio)).thenReturn(anuncio);

        Anuncio savedAnuncio = anuncioService.saveAnuncio(anuncio);

        assertNotNull(savedAnuncio);
        assertEquals(anuncio.getEstado(), savedAnuncio.getEstado());
        assertEquals(anuncio.getTipoCambio(), savedAnuncio.getTipoCambio());
    }

    @Test
    void getAnuncioById() {
        when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio));

        Optional<Anuncio> foundAnuncio = anuncioService.getAnuncioById(1L);

        assertTrue(foundAnuncio.isPresent());
        assertEquals(1L, foundAnuncio.get().getId());
    }

    @Test
    void updateAnuncio() {
        when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio));

        Anuncio updatedAnuncio = new Anuncio(movil, "Superviviente", "Intercambio");
        when(anuncioRepository.save(updatedAnuncio)).thenReturn(updatedAnuncio);

        Anuncio result = anuncioService.updateAnuncio(1L, updatedAnuncio);

        assertNotNull(result);
        assertEquals("Superviviente", result.getEstado());
        assertEquals("Intercambio", result.getTipoCambio());
    }

    @Test
    void deleteAnuncio() {
        when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio));

        anuncioService.deleteAnuncio(1L);

        when(anuncioRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Anuncio> deletedAnuncio = anuncioService.getAnuncioById(1L);

        assertFalse(deletedAnuncio.isPresent(), "El anuncio debería haber sido eliminado.");
    }
}

package com.example.apiwistonspring.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.example.apiwistonspring.model.entities.Dimensiones;
import com.example.apiwistonspring.model.entities.Marca;
import com.example.apiwistonspring.model.entities.Modelo;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.entities.Pantalla;
import com.example.apiwistonspring.model.entities.Procesador;
import com.example.apiwistonspring.model.entities.TecnologiaPantalla;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import com.example.apiwistonspring.services.AnuncioService;

class AnuncioServiceTest {

    @Mock
    private MovilRepository movilRepository;

    @InjectMocks
    private AnuncioService anuncioService;

    private Movil movil;

    @BeforeEach
    void setUp() {
        Marca marca = new Marca("Samsung");
        Modelo modelo = new Modelo(marca, 123456.4, "Samsung Galaxy S21");
        TecnologiaPantalla tecnologiaPantalla = new TecnologiaPantalla(101.5, "AMOLED");
        Pantalla pantalla = new Pantalla(tecnologiaPantalla, 6.2);
        Procesador procesador = new Procesador("Snapdragon 888", 123456L, 8, 2.84);
        Dimensiones dimensiones = new Dimensiones(15.0, 7.0, 0.8);

        movil = new Movil(128, dimensiones, LocalDate.now(), 12, true, 200, 599.99, 4, 4, 8,
                modelo, pantalla, 123456L, procesador, "Disponible", "Venta");
        movil.setId(1L);

    }

    @Test
    void publicarAnuncioEstadoValido() {
        String estado = "Disponible";
        String tipoCambio = "Venta";

        when(movilRepository.findById(1L)).thenReturn(Optional.of(movil));
        when(movilRepository.save(movil)).thenReturn(movil);

        Movil movilPublicado = anuncioService.publicarAnuncio(1L, estado, tipoCambio);

        assertNotNull(movilPublicado);
        assertEquals(estado, movilPublicado.getEstado());
        assertEquals(tipoCambio, movilPublicado.getTipoCambio());
    }

    @Test
    void publicarAnuncioEstadoInvalido() {
        String estado = "NoDisponible";
        String tipoCambio = "Venta";

        when(movilRepository.findById(1L)).thenReturn(Optional.of(movil));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anuncioService.publicarAnuncio(1L, estado, tipoCambio);
        });
        assertEquals("Estado no valido", exception.getMessage());
    }

    @Test
    void publicarAnuncioTipoCambioInvalido() {
        String estado = "Disponible";
        String tipoCambio = "Renta";

        when(movilRepository.findById(1L)).thenReturn(Optional.of(movil));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            anuncioService.publicarAnuncio(1L, estado, tipoCambio);
        });
        assertEquals("Tipo de cambio no valido", exception.getMessage());
    }

    @Test
    void publicarAnuncioMovilNoEncontrado() {
        String estado = "Disponible";
        String tipoCambio = "Venta";

        when(movilRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            anuncioService.publicarAnuncio(1L, estado, tipoCambio);
        });
        assertEquals("Movil no encontrado", exception.getMessage());
    }
}

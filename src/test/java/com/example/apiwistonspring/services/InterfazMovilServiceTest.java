package com.example.apiwistonspring.services;

import com.example.apiwistonspring.model.entities.*;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class InterfazMovilServiceTest {

    @Mock
    private MovilRepository movilRepository;

    @InjectMocks
    private MovilService movilService;

    private Movil movil;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Marca marca = new Marca("Samsung");
        Modelo modelo = new Modelo(marca, 123456L, "Samsung Galaxy S21");
        TecnologiaPantalla tecnologiaPantalla = new TecnologiaPantalla(101L, "AMOLED");
        Pantalla pantalla = new Pantalla(tecnologiaPantalla, 6.2);
        Procesador procesador = new Procesador("Snapdragon 888", 123456L, 8, 2.84);
        Dimensiones dimensiones = new Dimensiones(15.0, 7.0, 0.8);

        movil = new Movil(128, dimensiones, LocalDate.now(), 12, true, 200, 599.99,4,4, 8,
                modelo, pantalla, 123456L, procesador);

        // Asignamos un ID al movil, ya que en los mocks no se genera automáticamente
        movil.setId(1L);
    }

    @Test
    void saveMovil() {
        when(movilRepository.save(movil)).thenReturn(movil);

        Movil savedMovil = movilService.saveMovil(movil);

        assertNotNull(savedMovil);
        assertEquals(movil.getAlmacenamiento(), savedMovil.getAlmacenamiento());
    }

    @Test
    void getMovilById() {
        when(movilRepository.findById(1L)).thenReturn(Optional.of(movil));

        Optional<Movil> foundMovil = movilService.getMovilById(1L);

        assertTrue(foundMovil.isPresent());
        assertEquals(1L, foundMovil.get().getId());
    }

    @Test
    void getAllMoviles() {
        List<Movil> moviles = List.of(movil);

        when(movilRepository.findAll()).thenReturn(moviles);

        List<Movil> result = movilService.getAllMoviles();

        assertNotNull(result);
        assertFalse(result.isEmpty());

        assertEquals(movil, result.get(0));
    }

    @Test
    void updateMovil() {
        Marca marca = new Marca();
        marca.setId(1L);
        marca.setNombre("MarcaX");

        Modelo modelo = new Modelo(marca, 123L, "ModeloX");

        Movil movilExistente = new Movil(128, new Dimensiones(15.0, 7.0, 0.8),
                LocalDate.now(), 12, true,
                200, 599.99,4, 4, 8,
                modelo, new Pantalla(new TecnologiaPantalla(1L, "LCD"), 6.5),
                123456L, new Procesador("ProcesadorX", 4L, 4, 2.5));

        when(movilRepository.findById(1L)).thenReturn(Optional.of(movilExistente));

        Movil movilActualizado = new Movil(128, new Dimensiones(15.0, 7.0, 0.8),
                LocalDate.now(), 12, true,
                250, 699.99, 8,5, 8,
                modelo, new Pantalla(new TecnologiaPantalla(1L, "AMOLED"), 6.7),
                123456L, new Procesador("NuevoProcesadorX", 5L, 4, 3.0));

        when(movilRepository.save(movilActualizado)).thenReturn(movilActualizado);

        Movil updatedMovil = movilService.updateMovil(1L, movilActualizado);

        assertNotNull(updatedMovil);
        assertEquals(250, updatedMovil.getPeso());
        assertEquals(699.99, updatedMovil.getPrecio());
    }

    @Test
    void deleteMovil() {
        when(movilRepository.existsById(1L)).thenReturn(true);

        doNothing().when(movilRepository).deleteById(1L);

        movilService.deleteMovil(1L);

        when(movilRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Movil> movilEliminado = movilService.getMovilById(1L);

        assertFalse(movilEliminado.isPresent(), "El móvil debería haber sido eliminado.");
    }




}

package com.example.apiwistonspring.services;

import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.model.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ComparisionServiceTest {

    @Autowired
    ComparisionServiceImpl comparisionService;


    @Test
    void compareTwoMoviles() {


        Movil movil1 = new Movil(128, new Dimensiones(), LocalDate.now(), 48, true, 230.50, 200.99, 5, 8,68, new Modelo(), new Pantalla(), Long.valueOf(123456L), new Procesador());
        Movil movil2 = new Movil(256, new Dimensiones(), LocalDate.now(), 64, false, 180.75, 299.99, 8, 12,29, new Modelo(), new Pantalla(), Long.valueOf(654321L), new Procesador());

        List<MovilComparisionDTO> movils = comparisionService.compareTwoMoviles(1L, 2L);

        System.out.println(movil1);
        System.out.println(movil2);
        System.out.println(movils);


    }
}
package com.example.apiwistonspring.services;

import com.example.apiwistonspring.dtos.MovilComparisionDTO;
import com.example.apiwistonspring.model.entities.*;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ComparisionServiceTest {

    @Autowired
    ComparisionServiceImpl comparisionService;

    @Test
    void compareTwoMoviles() {

        List<MovilComparisionDTO> movils = comparisionService.compareTwoMoviles(1L, 2L);

        assertEquals(2, movils.size());


    }
}
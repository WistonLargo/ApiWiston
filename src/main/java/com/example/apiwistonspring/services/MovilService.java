package com.example.apiwistonspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apiwistonspring.dtos.MovilDTO;
import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.repositories.MovilRepository;

@Service
public class MovilService {

	@Autowired
    private MovilRepository movilRepository;

    /**
     * Obtiene los 5 móviles en tendencia según su popularidad.
     * @return Lista con los 5 móviles más populares.
     */
    public List<Movil> obtenerMovilesEnTendencia() {
        return movilRepository.findTop5ByOrderByPuntuacionDesc();
    }
	

}

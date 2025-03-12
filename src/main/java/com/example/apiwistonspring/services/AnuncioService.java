package com.example.apiwistonspring.services;

import com.example.apiwistonspring.model.entities.Movil;
import com.example.apiwistonspring.model.repositories.MovilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AnuncioService {

    @Autowired
    private MovilRepository movilRepository;

    public Movil publicarAnuncio(Long movilId, String estado, String tipoCambio) {
        if (estado == null || tipoCambio == null) {
            throw new IllegalArgumentException("El estado y tipo de cambio son obligatorios");
        }

        Optional<Movil> movilOpt = movilRepository.findById(movilId);
        if (movilOpt.isEmpty()) {
            throw new RuntimeException("Movil no encontrado");
        }
        
        Movil movil = movilOpt.get();
        
        if (!estado.equals("Disponible") && !estado.equals("Vendido") && !estado.equals("Reservado")) {
            throw new IllegalArgumentException("Estado no valido");
        }
        if (!tipoCambio.equals("Venta") && !tipoCambio.equals("Intercambio")) {
            throw new IllegalArgumentException("Tipo de cambio no valido");
        }
        movil.setEstado(estado);
        movil.setTipoCambio(tipoCambio);
        return movilRepository.save(movil);
    }
}

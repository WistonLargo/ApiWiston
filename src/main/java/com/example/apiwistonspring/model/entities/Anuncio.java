package com.example.apiwistonspring.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movil_id")
    private Movil movil;

    @NonNull
    private String estado; 
    @NonNull
    private String tipoCambio;

    private LocalDate fechaPublicacion = LocalDate.now();
    @NonNull
    private boolean contestado;

    public Anuncio(Movil movil, String estado, String tipoCambio) {
        this.movil = movil;
        this.estado = estado;
        this.tipoCambio = tipoCambio;
        this.contestado = false;
    }
}

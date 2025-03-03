package com.example.apiwistonspring.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private TecnologiaPantalla tecnologiaPantalla;
    @NonNull
    private double pantallaSize;
}

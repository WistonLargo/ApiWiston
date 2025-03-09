package com.example.apiwistonspring.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Procesador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nombre;
    @NonNull
    private Long referencia;
    @NonNull
    private int numeroNucleos;
    @NonNull
    private double gHz_max;
    
    @JsonIgnore
    @OneToMany(mappedBy = "procesador")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Movil> moviles;
    
    
}

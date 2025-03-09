package com.example.apiwistonspring.model.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Dimensiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private double alto;
    @NonNull
    private double ancho;
    @NonNull
    private double grosor;
    
    @JsonIgnore
    @OneToMany(mappedBy = "dimensiones")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Movil> moviles;
}

package com.example.apiwistonspring.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Marca marca;
    @NonNull
    private Long referencia;
    @NonNull
    private String nombre;
    
    public Long getMarcaId() {
		return marca.getId();

	}
}

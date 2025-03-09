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
public class TecnologiaPantalla {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    private Double referencia;
    
    @NonNull
    private String tipo;
    
    @JsonIgnore
    @OneToMany(mappedBy = "tecnologiaPantalla")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Pantalla> pantallas;
    
}

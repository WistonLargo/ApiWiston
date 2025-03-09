package com.example.apiwistonspring.model.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "marca_id")
    private Marca marca;
   
    @NonNull
    private Double referencia;
    
    @NonNull
    private String nombre;
    
    @JsonIgnore
    @OneToMany(mappedBy = "modelo")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Movil> moviles;
    
    public Long getMarcaId() {
		return marca.getId();

	}
}

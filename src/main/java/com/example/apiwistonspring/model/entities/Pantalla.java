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
public class Pantalla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tecnologia_pantalla_id") // Relación con TecnologiaPantalla
    private TecnologiaPantalla tecnologiaPantalla;
    
    @NonNull
    private double pantallaSize;
    
    @JsonIgnore
    @OneToMany(mappedBy = "pantalla")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Movil> moviles;
 
	public Long getPantallaId() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}

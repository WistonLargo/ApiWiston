package com.example.apiwistonspring.model.entities;

import com.example.apiwistonspring.enumeracion.EstadoSolicitud;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private UserEntity remitente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private UserEntity destinatario;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado;

    private LocalDateTime fechaCreacion;




}

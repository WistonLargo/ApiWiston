package com.example.apiwistonspring.model.repositories;

import com.example.apiwistonspring.enumeracion.EstadoSolicitud;
import com.example.apiwistonspring.model.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByDestinatarioIdAndEstado(Long destinatarioId, EstadoSolicitud estado);
    List<Solicitud> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
}

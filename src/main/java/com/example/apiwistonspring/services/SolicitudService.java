package com.example.apiwistonspring.services;

import com.example.apiwistonspring.enumeracion.EstadoSolicitud;
import com.example.apiwistonspring.model.entities.Solicitud;
import com.example.apiwistonspring.model.entities.UserEntity;
import com.example.apiwistonspring.model.repositories.SolicitudRepository;
import com.example.apiwistonspring.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SolicitudService {
    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UserRepository usuarioRepository;

    public Solicitud enviarSolicitud(String remitenteUsername, String destinatarioUsername) {
        UserEntity remitente = usuarioRepository.findByUsername(remitenteUsername)
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));

        UserEntity destinatario = usuarioRepository.findByUsername(destinatarioUsername)
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));

        Solicitud solicitud = new Solicitud();
        solicitud.setRemitente(remitente);
        solicitud.setDestinatario(destinatario);
        solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        solicitud.setFechaCreacion(LocalDateTime.now());

        return solicitudRepository.save(solicitud);
    }

    public Solicitud responderSolicitud(Long solicitudId, EstadoSolicitud nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (solicitud.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new RuntimeException("La solicitud ya ha sido respondida");
        }

        solicitud.setEstado(nuevoEstado);
        return solicitudRepository.save(solicitud);
    }
}

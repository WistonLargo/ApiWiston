package com.example.apiwistonspring.controllers;

import com.example.apiwistonspring.enumeracion.EstadoSolicitud;
import com.example.apiwistonspring.model.entities.Solicitud;
import com.example.apiwistonspring.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {
    @Autowired
    private SolicitudService solicitudService;

    // Endpoint para enviar una solicitud
    @PostMapping("/enviar")
    public ResponseEntity<Solicitud> enviarSolicitud(
            @RequestParam String remitenteUsername,
            @RequestParam String destinatarioUsername) {
        Solicitud solicitud = solicitudService.enviarSolicitud(remitenteUsername, destinatarioUsername);
        return ResponseEntity.ok(solicitud);
    }

    // Endpoint para responder una solicitud
    @PostMapping("/{solicitudId}/responder")
    public ResponseEntity<Solicitud> responderSolicitud(
            @PathVariable Long solicitudId,
            @RequestParam EstadoSolicitud estado) {
        Solicitud solicitud = solicitudService.responderSolicitud(solicitudId, estado);
        return ResponseEntity.ok(solicitud);
    }


    @GetMapping("/consultar")
    public ResponseEntity<List<Solicitud>> consultarSolicitudesPorFecha(@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) {
        List<Solicitud> solicitudes = solicitudService.consultarSolicitudesPorFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/numero-de-solicitudes")
    public ResponseEntity<Integer> numeroDeSolicitudesPorFecha(@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) {
        List<Solicitud> numeroDeSolicitudes = solicitudService.consultarSolicitudesPorFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(numeroDeSolicitudes.size());
    }
}

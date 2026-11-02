package mx.edu.enviofacil.infrastructure.controllers;

import mx.edu.enviofacil.application.usecases.ActualizarEstadoPaquete;
import mx.edu.enviofacil.domain.entities.Paquete;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/envios")
public class PaqueteController {

    private final ActualizarEstadoPaquete actualizarEstado;

    public PaqueteController(
            ActualizarEstadoPaquete actualizarEstado
    ) {
        this.actualizarEstado = actualizarEstado;
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(
            @PathVariable int id,
            @RequestBody SolicitudEstado solicitud
    ) {

        Paquete paquete = actualizarEstado.ejecutar(
                id,
                solicitud.EstadoPaquete(),
                solicitud.OperadorID()
        );

        return ResponseEntity.ok(
                new RespuestaEstado(
                        "Estado del paquete actualizado correctamente.",
                        paquete.getPaqueteID(),
                        paquete.getEstado().getDescripcion(),
                        paquete.getFechaCambio().toString()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> errorSolicitud(Exception e) {

        return ResponseEntity.badRequest().body(
                Map.of(
                        "codigo", "SOLICITUD_INVALIDA",
                        "mensaje", e.getMessage()
                )
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> paqueteNoEncontrado(Exception e) {

        return ResponseEntity.badRequest().body(
                Map.of(
                        "codigo", "SOLICITUD_INVALIDA",
                        "mensaje", e.getMessage()
                )
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> conflictoEstado(Exception e) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "codigo", "CONFLICTO_ESTADO",
                        "mensaje", e.getMessage()
                )
        );
    }

    public record SolicitudEstado(
            String EstadoPaquete,
            int OperadorID
    ) {}

    public record RespuestaEstado(
            String mensaje,
            int PaqueteID,
            String EstadoPaquete,
            String FechaCambio
    ) {}
}
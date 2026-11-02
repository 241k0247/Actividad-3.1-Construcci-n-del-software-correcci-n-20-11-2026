package mx.edu.enviofacil.domain.entities;

import mx.edu.enviofacil.domain.enums.EstadoPaquete;
import java.time.OffsetDateTime;
public class Paquete {

    private final int paqueteID;

    private EstadoPaquete estado;

    private OffsetDateTime fechaCambio;

    public Paquete(int paqueteID, EstadoPaquete estado) {

        if (paqueteID <= 0) {
            throw new IllegalArgumentException(
                    "El identificador debe ser positivo."
            );
        }

        this.paqueteID = paqueteID;
        this.estado = estado;
        this.fechaCambio = OffsetDateTime.now();
    }

    public void actualizarEstado(EstadoPaquete nuevoEstado) {

        if (nuevoEstado == null) {
            throw new IllegalArgumentException(
                    "El estado no puede estar vacío."
            );
        }

        // Transición documentada en AA2.3:
        // Almacén -> En tránsito.

        if (estado == EstadoPaquete.ALMACEN &&
                nuevoEstado == EstadoPaquete.EN_TRANSITO) {

            this.estado = nuevoEstado;
            this.fechaCambio = OffsetDateTime.now();

        } else {

            throw new IllegalStateException(
                    "La transición solicitada no está permitida."
            );
        }
    }

    public int getPaqueteID() {
        return paqueteID;
    }

    public EstadoPaquete getEstado() {
        return estado;
    }

    public OffsetDateTime getFechaCambio() {
        return fechaCambio;
    }
}

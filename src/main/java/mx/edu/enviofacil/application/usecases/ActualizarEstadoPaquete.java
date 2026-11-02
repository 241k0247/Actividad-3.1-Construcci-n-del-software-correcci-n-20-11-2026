package mx.edu.enviofacil.application.usecases;

import mx.edu.enviofacil.application.ports.PaqueteRepository;
import mx.edu.enviofacil.domain.entities.Paquete;
import mx.edu.enviofacil.domain.enums.EstadoPaquete;

import java.util.NoSuchElementException;

public class ActualizarEstadoPaquete {

    private final PaqueteRepository repository;

    public ActualizarEstadoPaquete(PaqueteRepository repository) {
        this.repository = repository;
    }

    public Paquete ejecutar(
            int paqueteID,
            String estadoNuevo,
            int operadorID
    ) {

        if (paqueteID <= 0 || operadorID <= 0) {
            throw new IllegalArgumentException(
                    "Los identificadores deben ser positivos."
            );
        }

        EstadoPaquete estado =
                EstadoPaquete.desdeTexto(estadoNuevo);

        Paquete paquete = repository.buscarPorId(paqueteID)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "El paquete no existe."
                        )
                );

        paquete.actualizarEstado(estado);

        repository.guardar(paquete);

        return paquete;
    }
}
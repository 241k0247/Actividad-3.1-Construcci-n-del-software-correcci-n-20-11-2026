package mx.edu.enviofacil.infrastructure.adapters;

import mx.edu.enviofacil.application.ports.PaqueteRepository;
import mx.edu.enviofacil.domain.entities.Paquete;
import mx.edu.enviofacil.domain.enums.EstadoPaquete;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPaqueteRepository
        implements PaqueteRepository {

    private final Map<Integer, Paquete> paquetes =
            new HashMap<>();

    public InMemoryPaqueteRepository() {

        // Datos iniciales para realizar pruebas.

        paquetes.put(
                1001,
                new Paquete(1001, EstadoPaquete.ALMACEN)
        );

        paquetes.put(
                1002,
                new Paquete(1002, EstadoPaquete.EN_TRANSITO)
        );
    }

    @Override
    public Optional<Paquete> buscarPorId(int paqueteID) {
        return Optional.ofNullable(paquetes.get(paqueteID));
    }

    @Override
    public void guardar(Paquete paquete) {
        paquetes.put(paquete.getPaqueteID(), paquete);
    }
}
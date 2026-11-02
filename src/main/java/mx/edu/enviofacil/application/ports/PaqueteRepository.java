package mx.edu.enviofacil.application.ports;

import mx.edu.enviofacil.domain.entities.Paquete;
import java.util.Optional;

public interface PaqueteRepository {

    Optional<Paquete> buscarPorId(int paqueteID);

    void guardar(Paquete paquete);
}

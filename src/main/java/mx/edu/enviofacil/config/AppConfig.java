package mx.edu.enviofacil.config;

import mx.edu.enviofacil.application.ports.PaqueteRepository;
import mx.edu.enviofacil.application.usecases.ActualizarEstadoPaquete;
import mx.edu.enviofacil.infrastructure.adapters.InMemoryPaqueteRepository;
import mx.edu.enviofacil.infrastructure.controllers.PaqueteController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PaqueteRepository paqueteRepository() {
        return new InMemoryPaqueteRepository();
    }

    @Bean
    public ActualizarEstadoPaquete actualizarEstadoPaquete(
            PaqueteRepository repository
    ) {
        return new ActualizarEstadoPaquete(repository);
    }

    @Bean
    public PaqueteController paqueteController(
            ActualizarEstadoPaquete casoUso
    ) {
        return new PaqueteController(casoUso);
    }
}
package mx.edu.enviofacil.domain.enums;

public enum EstadoPaquete {

    ALMACEN("Almacén"),
    PREPARADO("Preparado"),
    EN_TRANSITO("En tránsito"),
    RECIBIDO("Recibido");

    private final String descripcion;

    EstadoPaquete(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static EstadoPaquete desdeTexto(String texto) {

        for (EstadoPaquete estado : values()) {

            if (estado.descripcion.equals(texto)) {
                return estado;
            }
        }

        throw new IllegalArgumentException(
                "EstadoPaquete debe ser un estado permitido."
        );
    }
}

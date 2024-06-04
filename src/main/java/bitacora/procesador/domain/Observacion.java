package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Observaciones")
public class Observacion {
    @Id
    private long id;

    private String descripcion;

    private String mejora;


}

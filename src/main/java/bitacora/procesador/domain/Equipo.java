package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Equipos")
public class Equipo {
    @Id
    private long id;

    @ManyToOne
    private TipoEquipo tipoEquipo;

    private String marca;
}

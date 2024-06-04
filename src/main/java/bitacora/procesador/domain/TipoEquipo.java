package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TiposEquipo")
public class TipoEquipo {
    @Id
    private long id;

    private String nombre;

}

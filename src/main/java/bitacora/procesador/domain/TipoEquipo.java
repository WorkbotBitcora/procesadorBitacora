package bitacora.procesador.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TiposEquipo")
public class TipoEquipo {
    @Id
    private long id;

    private String nombre;

}

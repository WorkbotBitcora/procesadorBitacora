package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Recomendaciones")
public class Recomendacion {
    @Id
    private long id;

    private String recomendacion;

    private long id_chek;

    public Recomendacion() {
        this.recomendacion = "s";
        this.id_chek = 0;
    }

    public Recomendacion(long id_chek, String recomendacion, long id) {
        this.id_chek = id_chek;
        this.recomendacion = recomendacion;
        this.id = id;
    }
}

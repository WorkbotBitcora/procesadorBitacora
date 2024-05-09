package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Recomendaciones")
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String recomendacion;

    private long id_chek;


}

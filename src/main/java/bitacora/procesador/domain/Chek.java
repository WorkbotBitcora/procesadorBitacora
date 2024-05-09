package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Cheks")
public class Chek {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nombre;

    private long idTipoEquipo;

    private boolean estado;

    @ManyToMany(cascade = CascadeType.ALL )
    private List<Recomendacion> recomendacionList;

}

package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Cheks")
public class Chek {
    @Id
    private long id;

    private String nombre;

    private long idTipoEquipo;

    private boolean estado;

    @ManyToMany(cascade = CascadeType.ALL )
    private List<Recomendacion> recomendacionList;

    public Chek() {
    }

    public Chek(long id, String nombre, long idTipoEquipo, boolean estado, List<Recomendacion> recomendacionList) {
        this.id = id;
        this.nombre = nombre;
        this.idTipoEquipo = idTipoEquipo;
        this.estado = estado;
        this.recomendacionList = recomendacionList;
    }
}

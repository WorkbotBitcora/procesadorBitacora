package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "revisiones")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Observacion observacion;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Equipo equipo;

    @ManyToMany (cascade = CascadeType.PERSIST)
    private List<Chek> chekList;

    public Revision() {
    }

    public Revision(Observacion observacion, Equipo equipo, List<Chek> cheks) {
            this.observacion = observacion;
            this.equipo = equipo;
            this.chekList = cheks;
    }

    @PrePersist
    protected void onCreate(){
        fechaFinal = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        fechaFinal = new Date();
    }


}

package bitacora.procesador.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "revisiones")
public class Revision {
    @Id
    private long id;

    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @ManyToOne
    private Observacion observacion;

    @ManyToOne
    private Equipo equipo;

    @ManyToMany
    private List<Chek> chekList = new ArrayList<>();


    @PrePersist
    protected void onCreate(){
        fechaFinal = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        fechaFinal = new Date();
    }

    public Revision() {
    }

    public Revision(Observacion observacion, Equipo equipo, List<Chek> cheks) {
        this.observacion = observacion;
        this.equipo = equipo;
        this.chekList = cheks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Observacion getObservacion() {
        return observacion;
    }

    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Chek> getChekList() {
        return chekList;
    }

    public void setChekList(List<Chek> chekList) {
        this.chekList = chekList;
    }

    public void setChekList1(Chek chekList) {
        System.out.println("elemento que entra al la lista del chek :  " + chekList.getNombre() );
        this.chekList.add(chekList);
    }
}

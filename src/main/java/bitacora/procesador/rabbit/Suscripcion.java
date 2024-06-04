package bitacora.procesador.rabbit;

import bitacora.procesador.domain.*;

import bitacora.procesador.service.revision.RevisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Suscripcion {

    @Autowired
    private RevisionService revisionService;


    private long elemento;
    private long elementodos;
    private String data;


    @RabbitListener(queues = "queue.A")
    private void recivirDeA(Revision revision) {

        Revision revision1 = new Revision();
        revision1.setId(revision.getId());
        revision1.setFechaFinal(revision.getFechaFinal());
        //tipo equipo
        TipoEquipo tipoEquipo = new TipoEquipo();
        tipoEquipo.setId(revision.getEquipo().getTipoEquipo().getId());
        tipoEquipo.setNombre(revision.getEquipo().getTipoEquipo().getNombre());
        // equipo
        Equipo equipo = new Equipo();
        equipo.setId(revision.getEquipo().getId());
        equipo.setMarca(revision.getEquipo().getMarca());
        equipo.setTipoEquipo(tipoEquipo);
        revision1.setEquipo(equipo);
        //observacion
        Observacion observacion = new Observacion();
        observacion.setId(revision.getObservacion().getId());
        observacion.setMejora(revision.getObservacion().getMejora());
        observacion.setDescripcion(revision.getObservacion().getDescripcion());
        revision1.setObservacion(revision.getObservacion());

        List<Chek> cheks = new ArrayList<>();
        long id;
        String nombre;
        long idTipoEquipo;
        boolean estado;
        for (int i = 0; i < revision.getChekList().size(); i++) {
            id = (revision.getChekList().get(i).getId());
            nombre = (revision.getChekList().get(i).getNombre());
            idTipoEquipo = (revision.getChekList().get(i).getIdTipoEquipo());
            estado = (revision.getChekList().get(i).isEstado());
            cheks.add(new Chek(id, nombre, idTipoEquipo, estado, revision.getChekList().get(i).getRecomendacionList()));
        }

        revision1.setChekList(cheks);

        revisionService.GuardarRevision(revision1);

    }

/*
    @RabbitListener(queues = "queue.A")
    private void recivirDeA(String revision) {
        //revisionService.GuardarRevision(revision);
        log.info("Reciviendo prueba de A: " + revision);
    */

    @RabbitListener(queues = "queue.C")
    private void recivirDeC(long id) {
        System.out.println("El ID QUE LEE "  + id  );
        revisionService.mostrarRevision(id);
        log.info("id de revision a publicar: " + id);
    }

}

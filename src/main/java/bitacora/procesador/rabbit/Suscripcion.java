package bitacora.procesador.rabbit;


import bitacora.procesador.domain.Revision;
import bitacora.procesador.service.revision.RevisionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Suscripcion {
    @Autowired
    private RevisionService revisionService;

    @RabbitListener(queues = "queue.A")
    private void recivirDeA(Revision revision) {
        revisionService.GuardarRevision(revision);
        log.info("Reciviendo prueba de A: " + revision.getId());
    }

    @RabbitListener(queues = "queue.C")
    private void recivirDeC(long id) {
        revisionService.mostrarRevision(id);
        log.info("id de revision a publicar: " + id);
    }

}

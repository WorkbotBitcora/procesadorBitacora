package bitacora.procesador.service.revision;

import bitacora.procesador.domain.*;
import bitacora.procesador.rabbit.Publicador;
import bitacora.procesador.repository.*;
import bitacora.procesador.service.Check.ChekService;
import bitacora.procesador.service.recomendaciones.RecomendacionesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RevisionService {

    @Autowired
    private Publicador publicador;


    @Autowired
    private IObservacionRepository iObservacionRepository;

    @Autowired
    private IEquipoRepository iEquipoRepository;

    @Autowired
    private ITipoEquipoRepository iTipoEquipoRepository;
    @Autowired
    private IRevisionesRepository iRevisionesRepository;
    @Autowired
    private RecomendacionesService recomendacionesService;
    @Autowired
    private IChekRepository chekRepository;
    @Autowired
    private IRecomendacionRepository recomendacionRepository;

    @Autowired
    private ChekService chekService;


    private long elemento;
    private long elementodos;
    private String data;


    @Transactional
    public void GuardarRevision(Revision revision) {

        try {
            System.out.println("va a crear la observacion ");
            Observacion observacion = new Observacion();
            observacion.setId(revision.getObservacion().getId());
            System.out.println("id: " + observacion.getId());
            observacion.setMejora(revision.getObservacion().getMejora());
            System.out.println("mejora " + observacion.getMejora());
            observacion.setDescripcion(revision.getObservacion().getDescripcion());
            System.out.println("descripcion " + observacion.getDescripcion());

            iObservacionRepository.save(observacion);

            System.out.println("va a crear el tipo equipo ");
            TipoEquipo tipoEquipo = new TipoEquipo();
            tipoEquipo.setId(revision.getEquipo().getTipoEquipo().getId());
            tipoEquipo.setNombre(revision.getEquipo().getTipoEquipo().getNombre());
            iTipoEquipoRepository.save(tipoEquipo);

            System.out.println("va a crear el equipo ");
            Equipo equipo = new Equipo();
            equipo.setId(revision.getEquipo().getId());
            equipo.setMarca(revision.getEquipo().getMarca());
            equipo.setTipoEquipo(tipoEquipo);
            iEquipoRepository.save(equipo);

            //List<Chek> cheks = new ArrayList<>();
            Chek chek1 = new Chek();
            List<Recomendacion> recomendaciones = new ArrayList<>();

            for (int i = 0; i < revision.getChekList().size(); i++) {

                for (int j = 0; j < revision.getChekList().get(i).getRecomendacionList().size(); j++) {
                    elemento = revision.getChekList().get(i).getRecomendacionList().get(j).getId_chek();
                    elementodos = revision.getChekList().get(i).getRecomendacionList().get(j).getId();
                    data = revision.getChekList().get(i).getRecomendacionList().get(j).getRecomendacion();
                    Recomendacion recomendacion1 = new Recomendacion(elemento, data, elementodos);
                    recomendacionRepository.save(recomendacion1);
                    recomendaciones.add(recomendacion1);
                }
                chek1.setId(revision.getChekList().get(i).getId());
                chek1.setNombre(revision.getChekList().get(i).getNombre());
                chek1.setIdTipoEquipo(revision.getChekList().get(i).getIdTipoEquipo());
                chek1.setEstado(revision.getChekList().get(i).isEstado());
                chek1.setRecomendacionList(recomendaciones);
                chekRepository.save(chek1);
                recomendaciones.clear();
            }

            Revision rev = new Revision();
            rev.setId(revision.getId());
            rev.setEquipo(equipo);
            rev.setObservacion(observacion);
            rev.setChekList(revision.getChekList());
            rev.setFechaFinal(new Date());



            iRevisionesRepository.save(rev);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarRevision(long revisionID) {
        Revision revision = new Revision();
        try {
            iRevisionesRepository.findById(revisionID).ifPresent(data -> {
                revision.setId(data.getId());
                revision.setObservacion(data.getObservacion());
                revision.setEquipo(data.getEquipo());
                revision.setChekList(data.getChekList());
                revision.setFechaFinal(data.getFechaFinal());
            });
            System.out.println( publicador.publicar(revision));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

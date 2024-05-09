package bitacora.procesador.service.revision;

import bitacora.procesador.domain.*;
import bitacora.procesador.rabbit.Publicador;
import bitacora.procesador.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private IChekRepository ichekRepository;
    @Autowired
    private IRecomendacionRepository iRecomendacionRepository;
    @Autowired
    private IRevisionesRepository iRevisionesRepository;


   @Transactional
   public void GuardarRevision(Revision revision) {

       try {
           Observacion observacion = new Observacion();
           observacion.setId(revision.getObservacion().getId());
           observacion.setMejora(revision.getObservacion().getMejora());
           observacion.setDescripcion(revision.getObservacion().getDescripcion());
           iObservacionRepository.save(observacion);

           TipoEquipo tipoEquipo = new TipoEquipo();
           tipoEquipo.setId(revision.getEquipo().getTipoEquipo().getId());
           tipoEquipo.setNombre(revision.getEquipo().getTipoEquipo().getNombre());
           iTipoEquipoRepository.save(tipoEquipo);

           Equipo equipo = new Equipo();
           equipo.setId(revision.getEquipo().getId());
           equipo.setMarca(revision.getEquipo().getMarca());
           equipo.setTipoEquipo(tipoEquipo);
           iEquipoRepository.save(equipo);

           List<Chek> cheks = new ArrayList<>();
           List<Recomendacion> recomendaciones = new ArrayList<>();
           Recomendacion recomendacion = new Recomendacion();
           Chek chek = new Chek();
           for (int i = 0; i < revision.getChekList().size(); i++) {
               for (int j = 0; j < revision.getChekList().get(i).getRecomendacionList().size(); j++) {
                   recomendacion.setId(revision.getChekList().get(i).getRecomendacionList().get(j).getId());
                   recomendacion.setRecomendacion(revision.getChekList().get(i).getRecomendacionList().get(j).getRecomendacion());
                   recomendacion.setId_chek(revision.getChekList().get(i).getRecomendacionList().get(j).getId_chek());
                   iRecomendacionRepository.save(recomendacion);
                   recomendaciones.add(recomendacion);
               }
               chek.setId(revision.getChekList().get(i).getId());
               chek.setNombre(revision.getChekList().get(i).getNombre());
               chek.setEstado(revision.getChekList().get(i).isEstado());
               chek.setIdTipoEquipo(revision.getChekList().get(i).getIdTipoEquipo());
               chek.setRecomendacionList(recomendaciones);
               ichekRepository.save(chek);
               cheks.add(chek);
           }

           Revision rev = new Revision(observacion ,equipo, cheks);
           iRevisionesRepository.save(rev);
       }catch (Exception e){
           throw new RuntimeException(e);
       }
   }

   public void mostrarRevision(long revisionID) {
       Revision revision = new Revision();
       try {
           iRevisionesRepository.findById(revisionID).ifPresent(data ->{
               revision.setId(data.getId());
               revision.setObservacion(data.getObservacion());
               revision.setEquipo(data.getEquipo());
               revision.setChekList(data.getChekList());
               revision.setFechaFinal(data.getFechaFinal());
           });
           publicador.publicar(revision);
       }catch (Exception e){
           throw new RuntimeException(e);
       }
   }

}

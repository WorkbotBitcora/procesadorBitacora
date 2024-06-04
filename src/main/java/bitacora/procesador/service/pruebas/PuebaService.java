package bitacora.procesador.service.pruebas;

import bitacora.procesador.domain.*;
import bitacora.procesador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PuebaService {

    @Autowired
    private IChekRepository ichekRepository;
    @Autowired
    private IEquipoRepository iEquipoRepository;
    @Autowired
    private IObservacionRepository iObservacionRepository;
    @Autowired
    private IRevisionesRepository iRevisionesRepository;
    @Autowired
    private IRecomendacionRepository iRecomendacionRepository;
    @Autowired
    private ITipoEquipoRepository iTipoEquipoRepository;

    public String GuardarRevision (){
        try {
            TipoEquipo tipoEquipo = new TipoEquipo();
            tipoEquipo.setId(1);
            tipoEquipo.setNombre("Tipo Equipo");
            iTipoEquipoRepository.save(tipoEquipo);

            Equipo equipo = new Equipo();
            equipo.setTipoEquipo(tipoEquipo);
            equipo.setMarca("DELL");
            iEquipoRepository.save(equipo);

            Observacion observacion = new Observacion();
            observacion.setDescripcion("estos son los datos pertenecientes a las observaciones");
            observacion.setMejora("Esta es la mejora realizada a equipo revisado");
            iObservacionRepository.save(observacion);

            Recomendacion recomendacion = new Recomendacion();
            recomendacion.setId_chek(2);
            recomendacion.setRecomendacion("esta es la recomendacion para el chek creado normalmente ");
            iRecomendacionRepository.save(recomendacion);
            List<Recomendacion> recomendaciones = new ArrayList<Recomendacion>();
            recomendaciones.add(recomendacion);


            Chek chek = new Chek();
            chek.setRecomendacionList(recomendaciones);
            chek.setEstado(true);
            chek.setNombre("bateria");
            chek.setIdTipoEquipo(1);
            ichekRepository.save(chek);
            List<Chek> cheks = new ArrayList<>();
            cheks.add(chek);

            Revision revision = new Revision();

            revision.setId(4);
            revision.setChekList(cheks);
            revision.setObservacion(observacion);
            revision.setEquipo(equipo);
            revision.setFechaFinal(new Date());
            System.out.println("id : " + revision.getId());
            iRevisionesRepository.save(revision);

            return "Se Realizo con exito";
        }catch (Exception e){

            return "No se realizo el guardado" + e.getMessage();
        }
    }

    public List<Revision> obtenerRevisiones (){
        return iRevisionesRepository.findAll();
    }

}

package bitacora.procesador.service.recomendaciones;

import bitacora.procesador.domain.Recomendacion;
import bitacora.procesador.repository.IRecomendacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RecomendacionesService {

    @Autowired
    private IRecomendacionRepository recomendacionRepository;

    public void GuardarRecomendacion(Recomendacion recomendacion) {
        recomendacionRepository.save(recomendacion);
    }

}

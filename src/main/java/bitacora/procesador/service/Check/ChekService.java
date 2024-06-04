package bitacora.procesador.service.Check;

import bitacora.procesador.domain.Chek;
import bitacora.procesador.repository.IChekRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChekService {

    @Autowired
    private IChekRepository chekRepository;

    public void guardarChek(Chek chek){
        chekRepository.save(chek);
    }

}

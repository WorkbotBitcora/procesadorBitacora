package bitacora.procesador.repository;

import bitacora.procesador.domain.Chek;
import bitacora.procesador.domain.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IObservacionRepository extends JpaRepository<Observacion,Long> {
}

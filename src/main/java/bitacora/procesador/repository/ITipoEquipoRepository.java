package bitacora.procesador.repository;

import bitacora.procesador.domain.Chek;
import bitacora.procesador.domain.TipoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoEquipoRepository extends JpaRepository<TipoEquipo,Long> {
}

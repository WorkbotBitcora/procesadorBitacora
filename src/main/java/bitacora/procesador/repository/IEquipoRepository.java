package bitacora.procesador.repository;

import bitacora.procesador.domain.Chek;
import bitacora.procesador.domain.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEquipoRepository extends JpaRepository<Equipo,Long> {
}

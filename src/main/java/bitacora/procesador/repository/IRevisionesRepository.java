package bitacora.procesador.repository;


import bitacora.procesador.domain.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRevisionesRepository extends JpaRepository<Revision,Long> {


}

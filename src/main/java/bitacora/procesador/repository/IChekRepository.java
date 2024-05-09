package bitacora.procesador.repository;

import bitacora.procesador.domain.Chek;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChekRepository extends JpaRepository<Chek,Long> {
    @Transactional
    @Query(value = "select e from Chek  e  where e.idTipoEquipo = :ids ")
    List<Chek> mostrarChekPorIdEquipo(@Param("ids") long ids   );

    @Transactional
    @Query(value = "INSERT INTO public.cheks(id, estado, id_tipo_equipo, nombre) VALUES (ids, estados, idTipoEquipos, nombres)" , nativeQuery = true)
    void guardarChek(@Param("ids") long ids ,@Param("estados") boolean estados , @Param("idTipoEquipos") long idTipoEquipos , @Param("nombres") String nombres );


}

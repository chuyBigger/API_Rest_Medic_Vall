package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface MedicoRepository extends JpaRepository<Medico, Long> {


    Page<Medico> findAllByActivoTrue(Pageable paginacion);

    @Query(value = """
    select * from medico m
    where 
      m.activo = 1
      and m.especialidad = :especialidad
      and m.id not in (
        select c.medico_id from consulta c
        where c.fecha = :fecha
      )
    order by rand()
    limit 1
    """, nativeQuery = true)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(
            @Param("especialidad") Especialidad especialidad,
            @Param("fecha") LocalDateTime fecha);

}

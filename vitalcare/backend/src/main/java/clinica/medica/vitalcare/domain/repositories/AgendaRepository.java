package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Agenda;
import clinica.medica.vitalcare.domain.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findAllByPaciente(String nome);

//    @Query("SELECT a FROM agendas WHERE a.medico = :id")
//    List<Agenda> findByMedicoId(@Param("nome") Long id);
}

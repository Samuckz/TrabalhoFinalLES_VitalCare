package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Agenda;
import clinica.medica.vitalcare.domain.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findAllByPaciente(String nome);

    @Query("SELECT a FROM Agenda a WHERE a.medico.id = :id")
    List<Agenda> findAllByMedico(@Param("id") Long medicoId);

    boolean existsByMedicoAndData(Medico medico, LocalDateTime data);





//    List<Agenda> findByMedicoId(@Param("id") Long id);
}

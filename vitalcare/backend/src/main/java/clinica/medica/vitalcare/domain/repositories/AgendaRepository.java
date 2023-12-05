package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findAllByPaciente(String nome);
}

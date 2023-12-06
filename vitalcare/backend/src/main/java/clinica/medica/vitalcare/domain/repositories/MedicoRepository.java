package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Optional<Medico> findByCrm(String crm);
//    List<Medico> findAllByAtivoTrue();
}

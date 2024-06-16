package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.ProntuarioEletronico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<ProntuarioEletronico, Long> {
}

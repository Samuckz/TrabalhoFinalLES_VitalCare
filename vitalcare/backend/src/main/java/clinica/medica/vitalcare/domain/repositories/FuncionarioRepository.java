package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

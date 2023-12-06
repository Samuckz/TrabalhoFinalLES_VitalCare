package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByEmail(String email);

    Optional<Pessoa> findByNome(String nome);
}

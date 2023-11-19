package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.dtos.Endereco.EnderecoDto;
import clinica.medica.vitalcare.domain.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCep(String cep);

    Endereco getReferenceByCep(String cep);
}

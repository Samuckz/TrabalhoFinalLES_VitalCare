package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

//    @Query("""
//            SELECT p
//            FROM Paciente p
//            JOIN p.pessoa pessoa
//            WHERE pessoa.ativo = true;
//
//            """)
//    List<Paciente> findAllWithAtivoTrue();

    List<Paciente> findAllByPessoaAtivoTrue();

}

package clinica.medica.vitalcare.domain.repositories;

import clinica.medica.vitalcare.domain.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT p FROM Paciente p JOIN p.pessoa pe WHERE pe.nome = :nome")
    Optional<Paciente> findByPaciente(@Param("nome") String s);



}

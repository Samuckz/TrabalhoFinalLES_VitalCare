package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;
import clinica.medica.vitalcare.utils.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medico{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "funcionario", referencedColumnName = "id")
    private Funcionario funcionario;

    private Especialidade especialidade;

    private String crm;
}

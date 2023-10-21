package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.utils.enums.Especialidade;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medico extends Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Especialidade espscialidade;

    private String crm;


}

package clinica.medica.vitalcare.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Funcionario extends Pessoa{

    private Long idFuncionario;

    private LocalDateTime dataContrato;

    private double salario;

    private String senha;


}

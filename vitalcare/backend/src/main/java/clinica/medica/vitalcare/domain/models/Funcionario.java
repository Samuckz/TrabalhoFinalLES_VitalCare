package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "funcionarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Funcionario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    private Pessoa pessoa;

    private LocalDateTime dataContrato;

    private double salario;

    private String senha;

    public Funcionario(CadastrarFuncionarioDto dtoFuncionario) {
        this.pessoa = new Pessoa(dtoFuncionario.pessoa());
        this.dataContrato = dtoFuncionario.dataContrato();
        this.salario = dtoFuncionario.salario();
        this.senha = dtoFuncionario.senha();
    }
}

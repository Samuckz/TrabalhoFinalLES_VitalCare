package clinica.medica.vitalcare.domain.models;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idFuncionario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa_id;

    private LocalDateTime dataContrato;

    private double salario;

}

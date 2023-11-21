package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.domain.dtos.Paciente.CadastrarPacienteDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa", referencedColumnName = "id")
    private Pessoa pessoa;

    private double altura;

    private String tipoSanguineo;

    private double peso;

    public Paciente(CadastrarPacienteDto dto) {
        this.pessoa = new Pessoa(dto.pessoaDto());
        this.altura = dto.altura();
        this.tipoSanguineo = dto.tipoSanguineo();
        this.peso = dto.peso();
    }

    public void deletar() {
        this.pessoa.setAtivo(false);
    }
}

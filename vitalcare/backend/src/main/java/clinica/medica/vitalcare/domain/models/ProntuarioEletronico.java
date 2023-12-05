package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.domain.dtos.Prontuario.CadastrarProntuarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name = "prontuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProntuarioEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente", referencedColumnName = "id")
    private Paciente paciente;
    private String anamnese;
    private String medicamentos;
    private String atestados;
    private String exames;

    public ProntuarioEletronico(CadastrarProntuarioDto dto, Paciente paciente) {
        this.paciente = paciente;
        this.anamnese = dto.anamnese();
        this.medicamentos = dto.medicamentos();
        this.atestados = dto.atestados();
        this.exames = dto.exames();
    }
}

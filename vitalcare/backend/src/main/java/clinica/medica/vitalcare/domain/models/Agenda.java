package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "agendas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private String paciente;

    private String email_paciente;

    private String telefone_paciente;

    @ManyToOne
    @JoinColumn(name = "medico", referencedColumnName = "id")
    private Medico medico;


    public Agenda(CadastrarAgendaDto dto, Medico medico) {
        this.data = dto.data();
        this.paciente = dto.nomePaciente();
        this.email_paciente = dto.emailPaciente();
        this.telefone_paciente = dto.telefonePaciente();
        this.medico = medico;
    }
}

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
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    private Pessoa pessoa_id;

    private Especialidade especialidade;

    private String crm;

    private boolean ativo = true;


    public Medico(CadastrarMedicoDto dto) {
        this.pessoa_id = new Pessoa(dto.pessoa());
        this.especialidade = dto.especialidade();
        this.crm = dto.crm();
    }

    public void deletar(){
        this.ativo = false;
    }
}

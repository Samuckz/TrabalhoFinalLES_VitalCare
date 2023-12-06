package clinica.medica.vitalcare.domain.models;


import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private Boolean isMedico;

    public Usuario(CadastrarFuncionarioDto dto, boolean isMedico) {
        this.email = dto.pessoa().email();
        this.senha = dto.senha();
        this.isMedico = isMedico;
    }
}

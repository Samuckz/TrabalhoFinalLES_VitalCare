package clinica.medica.vitalcare.domain.dtos.Funcionario;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastrarFuncionarioDto(
        @NotNull CadastrarPessoaDto pessoa,
        @NotNull LocalDateTime dataContrato,
        @NotNull Double salario,

        @NotBlank String senha
        ) {
}

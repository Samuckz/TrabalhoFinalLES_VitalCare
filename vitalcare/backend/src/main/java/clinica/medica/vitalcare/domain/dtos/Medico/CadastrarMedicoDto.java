package clinica.medica.vitalcare.domain.dtos.Medico;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import clinica.medica.vitalcare.utils.enums.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarMedicoDto(@NotNull CadastrarPessoaDto pessoa, @NotBlank Especialidade especialidade, @NotBlank String crm) {
}

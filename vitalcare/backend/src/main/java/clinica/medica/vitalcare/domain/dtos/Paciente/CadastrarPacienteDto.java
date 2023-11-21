package clinica.medica.vitalcare.domain.dtos.Paciente;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarPacienteDto(
        @NotNull CadastrarPessoaDto pessoaDto,
        @NotNull double altura,
        @NotBlank String tipoSanguineo,
        @NotNull double peso
        ) {
}

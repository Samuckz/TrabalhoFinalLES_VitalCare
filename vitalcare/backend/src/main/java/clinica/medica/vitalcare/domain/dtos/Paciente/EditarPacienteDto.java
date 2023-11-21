package clinica.medica.vitalcare.domain.dtos.Paciente;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import clinica.medica.vitalcare.domain.dtos.Pessoa.EditarPessoaDto;

public record EditarPacienteDto(
        EditarPessoaDto pessoaDto,
        double altura,
        String tipoSanguineo,
        double peso
) {
}

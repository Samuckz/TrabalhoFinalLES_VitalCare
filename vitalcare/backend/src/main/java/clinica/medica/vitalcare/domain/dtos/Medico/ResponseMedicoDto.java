package clinica.medica.vitalcare.domain.dtos.Medico;

import clinica.medica.vitalcare.utils.enums.Especialidade;

public record ResponseMedicoDto(
        Long id,
        String nome,
        Especialidade especialidade) {
}

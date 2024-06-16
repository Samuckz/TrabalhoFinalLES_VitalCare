package clinica.medica.vitalcare.domain.dtos.Agenda;

import clinica.medica.vitalcare.utils.enums.Especialidade;

import java.time.LocalDateTime;

public record AgendaResponseDto(
        Long id,
        LocalDateTime data,
        String paciente,
        String medico,
        Especialidade especialidade
) {
}

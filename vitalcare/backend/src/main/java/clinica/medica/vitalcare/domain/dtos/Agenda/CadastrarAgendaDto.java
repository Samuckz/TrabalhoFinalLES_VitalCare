package clinica.medica.vitalcare.domain.dtos.Agenda;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastrarAgendaDto(
        @NotNull @Future LocalDateTime data,
        @NotBlank String nomePaciente,
        @NotBlank Long medicoId
        ) {
}

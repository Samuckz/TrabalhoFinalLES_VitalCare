package clinica.medica.vitalcare.domain.dtos.Prontuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarProntuarioDto(
        @NotNull Long pacienteId,
        String anamnese,
        String medicamentos,
        String atestados,
        String exames

) {}

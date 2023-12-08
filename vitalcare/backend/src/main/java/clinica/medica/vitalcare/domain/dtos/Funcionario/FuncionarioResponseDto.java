package clinica.medica.vitalcare.domain.dtos.Funcionario;

import java.time.LocalDateTime;

public record FuncionarioResponseDto(
        Long id,
        String nome,
        String email,

        LocalDateTime dataContrato

        ) {
}

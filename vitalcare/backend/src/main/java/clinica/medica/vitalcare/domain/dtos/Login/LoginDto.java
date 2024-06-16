package clinica.medica.vitalcare.domain.dtos.Login;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank String email,
        @NotBlank String senha
) {
}

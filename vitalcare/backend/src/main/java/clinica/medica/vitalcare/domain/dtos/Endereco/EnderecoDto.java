package clinica.medica.vitalcare.domain.dtos.Endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDto(@NotBlank String cep, @NotBlank String logradouro, @NotBlank String bairro, @NotBlank String cidade, @NotBlank String estado) {
}

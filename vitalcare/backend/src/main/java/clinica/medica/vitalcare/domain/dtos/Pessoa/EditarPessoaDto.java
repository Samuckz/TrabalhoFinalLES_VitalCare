package clinica.medica.vitalcare.domain.dtos.Pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditarPessoaDto(
        String nome,
        String telefone,
        String email,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado
) {
}

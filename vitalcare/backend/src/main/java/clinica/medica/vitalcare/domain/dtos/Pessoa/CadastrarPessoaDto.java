package clinica.medica.vitalcare.domain.dtos.Pessoa;

import clinica.medica.vitalcare.domain.dtos.Endereco.EnderecoDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarPessoaDto(
        @NotBlank String nome,
        @NotBlank String telefone,
        @NotBlank @Email String email,
        @NotBlank String cep,
        @NotBlank String logradouro,
        @NotBlank String bairro,
        @NotBlank String cidade,
        @NotBlank String estado) {
}

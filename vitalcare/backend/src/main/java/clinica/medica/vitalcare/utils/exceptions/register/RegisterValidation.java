package clinica.medica.vitalcare.utils.exceptions.register;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;

public interface RegisterValidation {
    void validar(CadastrarPessoaDto dto);
}

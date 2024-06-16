package clinica.medica.vitalcare.utils.exceptions.register.Funcionario;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;

public interface RegisterValidation {
    void validar(CadastrarFuncionarioDto dto);
}

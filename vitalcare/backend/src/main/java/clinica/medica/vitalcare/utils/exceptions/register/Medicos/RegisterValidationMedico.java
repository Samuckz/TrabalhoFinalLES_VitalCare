package clinica.medica.vitalcare.utils.exceptions.register.Medicos;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;

public interface RegisterValidationMedico {
    void validar(CadastrarMedicoDto dto);
}

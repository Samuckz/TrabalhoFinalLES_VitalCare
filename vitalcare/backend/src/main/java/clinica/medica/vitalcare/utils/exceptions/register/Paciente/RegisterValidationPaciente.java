package clinica.medica.vitalcare.utils.exceptions.register.Paciente;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.CadastrarPacienteDto;

public interface RegisterValidationPaciente {
    void validar(CadastrarPacienteDto dto);
}

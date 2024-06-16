package clinica.medica.vitalcare.utils.exceptions.register.Paciente;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.CadastrarPacienteDto;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePessoaValidation implements RegisterValidationPaciente {

    @Autowired
    PessoaRepository pessoaRepository;
    @Override
    public void validar(CadastrarPacienteDto dto) {
        var pessoa = pessoaRepository.findByNome(dto.pessoaDto().nome());
        if(pessoa.isPresent())
            throw new ValidationException("Pessoa já cadastrada no sistema");
    }
}

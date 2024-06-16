package clinica.medica.vitalcare.utils.exceptions.register.Medicos;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.RegisterValidation;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePessoaValidationMedico implements RegisterValidationMedico {

    @Autowired
    PessoaRepository pessoaRepository;
    @Override
    public void validar(CadastrarMedicoDto dto) {
        var pessoa = pessoaRepository.findByNome(dto.funcionario().pessoa().nome());
        if(pessoa.isPresent())
            throw new ValidationException("Pessoa já cadastrada no sistema");
    }
}

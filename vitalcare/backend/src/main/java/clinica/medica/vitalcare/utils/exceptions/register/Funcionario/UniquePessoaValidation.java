package clinica.medica.vitalcare.utils.exceptions.register.Funcionario;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePessoaValidation implements RegisterValidation {

    @Autowired
    PessoaRepository pessoaRepository;
    @Override
    public void validar(CadastrarFuncionarioDto dto) {
        var pessoa = pessoaRepository.findByNome(dto.pessoa().nome());
        if(pessoa.isPresent())
            throw new ValidationException("Pessoa já cadastrada no sistema");
    }
}

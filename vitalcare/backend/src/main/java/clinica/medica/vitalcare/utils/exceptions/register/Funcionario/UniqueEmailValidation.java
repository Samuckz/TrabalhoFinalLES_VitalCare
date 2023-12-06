package clinica.medica.vitalcare.utils.exceptions.register.Funcionario;


import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidation implements RegisterValidation {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public void validar(CadastrarFuncionarioDto dto) {
        var user = pessoaRepository.findByEmail(dto.pessoa().email());

        if(user != null){
            throw new ValidationException("Email já cadastrado no sistema");
        }
    }
}

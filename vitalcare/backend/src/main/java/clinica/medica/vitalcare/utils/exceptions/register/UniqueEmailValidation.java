package clinica.medica.vitalcare.utils.exceptions.register;


import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidation implements RegisterValidation{

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public void validar(CadastrarPessoaDto dto) {
        var user = pessoaRepository.findByEmail(dto.email());

        if(user != null){
            throw new ValidationException("Email já cadastrado no sistema");
        }
    }
}

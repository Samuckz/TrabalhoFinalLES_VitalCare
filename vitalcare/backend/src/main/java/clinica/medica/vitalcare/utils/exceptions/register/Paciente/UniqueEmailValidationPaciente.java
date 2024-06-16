package clinica.medica.vitalcare.utils.exceptions.register.Paciente;


import clinica.medica.vitalcare.domain.dtos.Paciente.CadastrarPacienteDto;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidationPaciente implements RegisterValidationPaciente {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public void validar(CadastrarPacienteDto dto) {
        var user = pessoaRepository.findByEmail(dto.pessoaDto().email());

        if(user != null){
            throw new ValidationException("Email já cadastrado no sistema");
        }
    }
}

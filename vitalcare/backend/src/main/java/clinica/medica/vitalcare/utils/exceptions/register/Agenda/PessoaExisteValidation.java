package clinica.medica.vitalcare.utils.exceptions.register.Agenda;

import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;
import clinica.medica.vitalcare.domain.repositories.PacienteRepository;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.services.PessoaService;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaExisteValidation implements RegisterValidationAgenda{

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public void validar(CadastrarAgendaDto dto) {
        var paciente = pacienteRepository.findByPaciente(dto.nomePaciente());
        if(paciente.isEmpty())
            throw new ValidationException("Paciente não existe no sistema, favor cadastrá-lo");
    }
}

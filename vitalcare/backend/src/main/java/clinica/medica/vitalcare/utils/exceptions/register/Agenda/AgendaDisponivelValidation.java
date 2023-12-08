package clinica.medica.vitalcare.utils.exceptions.register.Agenda;

import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;
import clinica.medica.vitalcare.domain.repositories.AgendaRepository;
import clinica.medica.vitalcare.domain.repositories.MedicoRepository;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendaDisponivelValidation implements RegisterValidationAgenda{

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public void validar(CadastrarAgendaDto dto) {
        var medico = medicoRepository.findById(dto.medicoId());

        var horarioIndisponivel = agendaRepository.existsByMedicoAndData(medico.get(), dto.data());

        System.out.println("Disponivel = " + horarioIndisponivel);

        if (horarioIndisponivel){
            throw new ValidationException("O médico estará em consulta");
        }

    }
}

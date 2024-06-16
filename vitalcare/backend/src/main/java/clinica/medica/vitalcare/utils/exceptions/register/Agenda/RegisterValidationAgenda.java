package clinica.medica.vitalcare.utils.exceptions.register.Agenda;

import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;

public interface RegisterValidationAgenda {
    void validar(CadastrarAgendaDto dto);
}

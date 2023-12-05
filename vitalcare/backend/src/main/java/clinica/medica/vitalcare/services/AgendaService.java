package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Agenda.AgendaResponseDto;
import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;
import clinica.medica.vitalcare.domain.models.Agenda;
import clinica.medica.vitalcare.domain.repositories.AgendaRepository;
import clinica.medica.vitalcare.domain.repositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {
    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    public ResponseEntity cadastrar(CadastrarAgendaDto dto) {
        var medico = medicoRepository.findById(dto.medicoId());
        if(medico.isEmpty())
            throw new EntityNotFoundException("Médico não encontrado no sistema");

        var agenda = new Agenda(dto, medico.get());
        agendaRepository.save(agenda);
        return new ResponseEntity(agenda, HttpStatus.OK);
    }

    public ResponseEntity<List<AgendaResponseDto>> listarAgendas() {
        var agendas = agendaRepository.findAll();
        var response = agendas.stream().map(m -> {
            return new AgendaResponseDto(
                    m.getId(),
                    m.getData(),
                    m.getPaciente(),
                    m.getMedico().getFuncionario().getPessoa().getNome(),
                    m.getMedico().getEspecialidade());

        }).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<List<AgendaResponseDto>> listarPorPaciente(String nome) {
        var agendas = agendaRepository.findAllByPaciente(nome);
        var response = agendas.stream().map(m -> {
            return new AgendaResponseDto(
                    m.getId(),
                    m.getData(),
                    m.getPaciente(),
                    m.getMedico().getFuncionario().getPessoa().getNome(),
                    m.getMedico().getEspecialidade());

        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

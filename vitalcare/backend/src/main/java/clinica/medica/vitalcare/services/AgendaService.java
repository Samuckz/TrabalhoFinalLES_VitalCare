package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Agenda.AgendaResponseDto;
import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;
import clinica.medica.vitalcare.domain.models.Agenda;
import clinica.medica.vitalcare.domain.models.Medico;
import clinica.medica.vitalcare.domain.repositories.AgendaRepository;
import clinica.medica.vitalcare.domain.repositories.MedicoRepository;
import clinica.medica.vitalcare.domain.repositories.PacienteRepository;
import clinica.medica.vitalcare.utils.exceptions.register.Agenda.RegisterValidationAgenda;
import clinica.medica.vitalcare.utils.exceptions.register.Paciente.RegisterValidationPaciente;
import clinica.medica.vitalcare.utils.infra.security.TokenService;
import clinica.medica.vitalcare.utils.infra.security.securityFilter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaService {
    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    securityFilter securityFilter;

    @Autowired
    private List<RegisterValidationAgenda> validadoresAgenda;


    public ResponseEntity cadastrar(CadastrarAgendaDto dto) throws Exception {

        validadoresAgenda.forEach(v -> v.validar(dto));

        var medico = medicoRepository.findById(dto.medicoId());
        var paciente = pacienteRepository.findByPaciente(dto.nomePaciente());
        if(medico.isEmpty())
            throw new Exception("Médico não encontrado no sistema");

        if (paciente.isEmpty())
            throw new Exception("Paciente não encontrado no sistema");

        var agenda = new Agenda(dto, medico.get(), paciente.get());
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


    public ResponseEntity<List<AgendaResponseDto>> listarPorPaciente(String nome) throws Exception {
        var agendas = agendaRepository.findAllByPaciente(nome);
        if(agendas.isEmpty())
            throw new Exception("Paciente não encontrado no sistema");


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

    public ResponseEntity listarPorMedico(HttpServletRequest request) throws Exception {
//        return new ResponseEntity<>("Agenda de medicos", HttpStatus.OK);
//        var token = securityFilter.getToken(request);
        var token = request.getHeader("Authorization");
        token = token.replace("Bearer ", "").trim();
        System.out.println("Token: "+token);
        var currentMedico = tokenService.getMedicoId(token);
        System.out.println("CurrentMedico: "+currentMedico);
        var agendas = agendaRepository.findAllByMedico(currentMedico);

        if(agendas.isEmpty())
            throw new Exception("O médico em questão não possui agendas cadastradas");

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

package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Agenda.AgendaResponseDto;
import clinica.medica.vitalcare.domain.dtos.Agenda.CadastrarAgendaDto;
import clinica.medica.vitalcare.domain.models.Agenda;
import clinica.medica.vitalcare.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agendas")
public class AgendaController {
    @Autowired
    AgendaService agendaService;

    @PostMapping("cadastrar")
    public ResponseEntity cadastrarAgenda(@RequestBody CadastrarAgendaDto dto) throws Exception {
        return agendaService.cadastrar(dto);
    }

    @GetMapping("listarAgendas")
    public ResponseEntity<List<AgendaResponseDto>> listarAgendas(){
        return agendaService.listarAgendas();
    }

    @GetMapping("agenda/{nome}")
    public ResponseEntity<List<AgendaResponseDto>> listarAgendaPorPaciente(@PathVariable String nome) throws Exception {
        return agendaService.listarPorPaciente(nome);
    }

//    @GetMapping("agendaMedico/{id}")
//    public ResponseEntity<List<AgendaResponseDto>> listarAgendaPorMedico(@PathVariable Long medicoId) throws Exception {
//        return agendaService.listarPorMedico(medicoId);
//    }
}

package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Paciente.CadastrarPacienteDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.EditarPacienteDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.PacienteResponseDto;
import clinica.medica.vitalcare.domain.models.Paciente;
import clinica.medica.vitalcare.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody CadastrarPacienteDto dto){
        return pacienteService.cadastrar(dto);
    }

    @GetMapping("/listar-pacientes")
    public ResponseEntity<List<PacienteResponseDto>> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @GetMapping("paciente/{id}")
    public ResponseEntity<PacienteResponseDto> pesquisarPaciente(@PathVariable Long id){
        return pacienteService.pesquisarPaciente(id);
    }

    @PutMapping("editar-paciente/{id}")
    public ResponseEntity<PacienteResponseDto> editarPaciente(@RequestBody EditarPacienteDto dto, @PathVariable Long id){
        return pacienteService.editarPaciente(dto, id);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarPaciente(@PathVariable Long id){
        return pacienteService.deletarPaciente(id);
    }

}

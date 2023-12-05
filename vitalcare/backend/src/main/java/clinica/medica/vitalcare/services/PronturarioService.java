package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Paciente.PacienteResponseDto;
import clinica.medica.vitalcare.domain.dtos.Prontuario.CadastrarProntuarioDto;
import clinica.medica.vitalcare.domain.dtos.Prontuario.ProntuarioResponseDto;
import clinica.medica.vitalcare.domain.models.ProntuarioEletronico;
import clinica.medica.vitalcare.domain.repositories.PacienteRepository;
import clinica.medica.vitalcare.domain.repositories.ProntuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PronturarioService {
    @Autowired
    ProntuarioRepository prontuarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;


    public ResponseEntity cadastrar(CadastrarProntuarioDto dto) {
        var paciente = pacienteRepository.findById(dto.pacienteId());
        if(paciente.isEmpty())
            throw new EntityNotFoundException("Paciente não encontrado");

        var prontuario = new ProntuarioEletronico(dto, paciente.get());
        prontuarioRepository.save(prontuario);
        return new ResponseEntity(prontuario, HttpStatus.OK);
    }

    public ResponseEntity<List<ProntuarioResponseDto>> listarProntuarios() {
        var prontuarios = prontuarioRepository.findAll();
        var response = prontuarios.stream().map(m -> {
            return new ProntuarioResponseDto(
                    m.getId(),
                    m.getPaciente().getPessoa().getNome(),
                    m.getAnamnese(),
                    m.getMedicamentos(),
                    m.getAtestados(),
                    m.getExames()
                    );
        }).toList();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

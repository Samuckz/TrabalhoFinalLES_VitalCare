package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Funcionario.FuncionarioResponseDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.PacienteResponseDto;
import clinica.medica.vitalcare.domain.models.Funcionario;
import clinica.medica.vitalcare.domain.repositories.FuncionarioRepository;
import clinica.medica.vitalcare.domain.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    public ResponseEntity cadastrar(CadastrarFuncionarioDto dto) {
        Funcionario funcionario = new Funcionario(dto);
        funcionarioRepository.save(funcionario);
        return new ResponseEntity(funcionario, HttpStatus.OK);
    }

    public ResponseEntity<List<FuncionarioResponseDto>> listarFuncionarios() {
        var funcionarios = funcionarioRepository.findAll();
        var response = funcionarios.stream().map(m -> {
            return new FuncionarioResponseDto(
                    m.getPessoa().getNome(),
                    m.getPessoa().getEmail(),
                    m.getDataContrato());

        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<FuncionarioResponseDto> getFuncionarioById(Long id) throws Exception {
        var funcionario = funcionarioRepository.findById(id);
        if(funcionario.isEmpty())
            throw new Exception("Funcionario não encontrado");

        var response = new FuncionarioResponseDto(
                funcionario.get().getPessoa().getNome(),
                funcionario.get().getPessoa().getEmail(),
                funcionario.get().getDataContrato()
            );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

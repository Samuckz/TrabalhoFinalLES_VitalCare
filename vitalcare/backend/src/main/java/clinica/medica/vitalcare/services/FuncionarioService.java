package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Funcionario.FuncionarioResponseDto;
import clinica.medica.vitalcare.domain.models.Funcionario;
import clinica.medica.vitalcare.domain.models.Usuario;
import clinica.medica.vitalcare.domain.repositories.FuncionarioRepository;
import clinica.medica.vitalcare.domain.repositories.UsuarioRepository;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.RegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private List<RegisterValidation> validadoresFuncionario;

    // === CRUD DE FUNCIONÁRIOS ===

    public ResponseEntity cadastrar(CadastrarFuncionarioDto dto) {

        validadoresFuncionario.forEach(v -> v.validar(dto));

        Funcionario funcionario = new Funcionario(dto);
        Usuario user = new Usuario(dto, false);
        funcionarioRepository.save(funcionario);
        usuarioRepository.save(user);
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

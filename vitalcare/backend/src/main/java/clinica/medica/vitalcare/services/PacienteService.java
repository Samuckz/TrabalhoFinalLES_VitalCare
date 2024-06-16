package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Paciente.CadastrarPacienteDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.EditarPacienteDto;
import clinica.medica.vitalcare.domain.dtos.Paciente.PacienteResponseDto;
import clinica.medica.vitalcare.domain.models.Paciente;
import clinica.medica.vitalcare.domain.models.Pessoa;
import clinica.medica.vitalcare.domain.repositories.PacienteRepository;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.RegisterValidation;
import clinica.medica.vitalcare.utils.exceptions.register.Paciente.RegisterValidationPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private List<RegisterValidationPaciente> validadoresPaciente;

    public ResponseEntity<Paciente> cadastrar(CadastrarPacienteDto dto) {
        validadoresPaciente.forEach(v -> v.validar(dto));
        var paciente = new Paciente(dto);
        pacienteRepository.save(paciente);
        return new ResponseEntity<Paciente>(paciente, HttpStatus.CREATED);
    }

    public ResponseEntity<List<PacienteResponseDto>> listarPacientes() {
        var pacientes = pacienteRepository.findAllByPessoaAtivoTrue();
        var response = pacientes.stream().map(m -> {
            return new PacienteResponseDto(
                    m.getPessoa().getNome(),
                    m.getPessoa().getEmail(),
                    m.getPessoa().getTelefone(),
                    m.getAltura(),
                    m.getTipoSanguineo(),
                    m.getPeso());
        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity pesquisarPaciente(Long id) {
        var paciente = pacienteRepository.findById(id);
        if(paciente.isEmpty())
            return new ResponseEntity<>("O paciente de id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);

        var response = new PacienteResponseDto(
                    paciente.get().getPessoa().getNome(),
                    paciente.get().getPessoa().getEmail(),
                    paciente.get().getPessoa().getTelefone(),
                    paciente.get().getAltura(),
                    paciente.get().getTipoSanguineo(),
                    paciente.get().getPeso()
                );
        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity editarPaciente(EditarPacienteDto dto, Long id) {
        var paciente = pacienteRepository.findById(id);
        if(paciente.isEmpty())
            return new ResponseEntity<>("Paciente de id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);
        var response = paciente.get();

        if(dto.pessoaDto() != null){
            var pessoa = response.getPessoa();
            if (dto.pessoaDto().nome() != null) {
                pessoa.setNome(dto.pessoaDto().nome());
            }

            if (dto.pessoaDto().telefone() != null) {
                pessoa.setTelefone(dto.pessoaDto().telefone());
            }

            if (dto.pessoaDto().email() != null) {
                pessoa.setEmail(dto.pessoaDto().email());
            }

            if (dto.pessoaDto().cep() != null) {
                pessoa.setCep(dto.pessoaDto().cep());
            }

            if (dto.pessoaDto().logradouro() != null) {
                pessoa.setLogradouro(dto.pessoaDto().logradouro());
            }

            if (dto.pessoaDto().bairro() != null) {
                pessoa.setBairro(dto.pessoaDto().bairro());
            }

            if (dto.pessoaDto().cidade() != null) {
                pessoa.setCidade(dto.pessoaDto().cidade());
            }

            if (dto.pessoaDto().estado() != null) {
                pessoa.setEstado(dto.pessoaDto().estado());
            }

            response.setPessoa(pessoa);

        }

        if(dto.altura() != 0) {
            response.setAltura(dto.altura());
        }

        if(dto.tipoSanguineo() != null){
            response.setTipoSanguineo(dto.tipoSanguineo());
        }

        if(dto.peso() != 0){
            response.setPeso(dto.peso());
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity deletarPaciente(Long id) {
        var paciente = pacienteRepository.findById(id);
        if(paciente.isEmpty())
            return new ResponseEntity<>("Paciente de id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);
        var response = paciente.get();

        response.deletar();

        pacienteRepository.save(response);

        return new ResponseEntity("Paciente deletado com sucesso!", HttpStatus.OK);

    }



}

package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;
import clinica.medica.vitalcare.domain.dtos.Medico.ResponseMedicoDto;
import clinica.medica.vitalcare.domain.models.Medico;
import clinica.medica.vitalcare.domain.models.Usuario;
import clinica.medica.vitalcare.domain.repositories.MedicoRepository;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.domain.repositories.UsuarioRepository;
import clinica.medica.vitalcare.utils.enums.Especialidade;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.RegisterValidation;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.UniqueEmailValidation;
import clinica.medica.vitalcare.utils.exceptions.register.Medicos.RegisterValidationMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.List;

@Service
public class MedicoService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UniqueEmailValidation uniqueEmailValidation;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    private List<RegisterValidationMedico> validadoresMedico;

    @Autowired
    private List<RegisterValidation> validadoresFuncionario;

    @Autowired
    PasswordEncoder encoder;


    public ResponseEntity<ResponseMedicoDto> cadastrar(CadastrarMedicoDto dto) {
        validadoresMedico.forEach(v -> v.validar(dto));
        validadoresFuncionario.forEach(v -> v.validar(dto.funcionario()));

        var medico = new Medico(dto);

        var func_medico = medico.getFuncionario();

        func_medico.setSenha(encoder.encode(func_medico.getSenha()));

        medico.setFuncionario(func_medico);
        var medicoDb = medicoRepository.save(medico);
        var user = new Usuario(dto.funcionario(), medicoDb.getFuncionario().getSenha() , true, medicoDb.getId());
        usuarioRepository.save(user);
        var response = new ResponseMedicoDto(medicoDb.getId(), medicoDb.getFuncionario().getPessoa().getNome(), medicoDb.getEspecialidade());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ResponseMedicoDto>> listarMedicos() {
        var medicos = medicoRepository.findAll();
        var response = medicos.stream().map(m -> {
            return new ResponseMedicoDto(m.getId(), m.getFuncionario().getPessoa().getNome(), m.getEspecialidade());
        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<ResponseMedicoDto>> listarMedicosPorEspecialidade(Especialidade especialidade) {
        var medicos = medicoRepository.findAllByEspecialidade(especialidade);
        var response = medicos.stream().map(m -> {
            return new ResponseMedicoDto(m.getId(), m.getFuncionario().getPessoa().getNome(), m.getEspecialidade());
        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
//
//    public ResponseEntity getMedicoById(Long id) {
//        var medico = medicoRepository.findById(id);
//        if(medico.isEmpty())
//            return new ResponseEntity("Médico com id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);
//
//        var response = new ResponseMedicoDto(id, medico.get().getPessoa_id().getNome(), medico.get().getEspecialidade());
//        return new ResponseEntity<>(response, HttpStatus.OK);
//
//    }
//
//    public ResponseEntity editarMedico(Long id, CadastrarMedicoDto dto) {
//        var medico = medicoRepository.findById(id);
//
//        if(medico.isEmpty())
//            return new ResponseEntity("O médico de id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);
//
//        var response = medico.get();
//        if(dto.pessoa() != null){
//            var pessoa = new Pessoa(dto.pessoa());
//            response.setPessoa_id(pessoa);
//        }
//
//        if(dto.especialidade() != null){
//            response.setEspecialidade(dto.especialidade());
//        }
//
//        if(dto.crm() != null){
//            response.setCrm(dto.crm());
//        }
//
//        medicoRepository.save(response);
//
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    public ResponseEntity excluir(Long id) {
//        var medico = medicoRepository.getReferenceById(id);
//        medico.deletar();
//        medicoRepository.save(medico);
//        return new ResponseEntity("Médico deletado com sucesso!", HttpStatus.OK);
//
//    }
}

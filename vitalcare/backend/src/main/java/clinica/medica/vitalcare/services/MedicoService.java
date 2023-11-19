package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;
import clinica.medica.vitalcare.domain.dtos.Medico.ResponseMedicoDto;
import clinica.medica.vitalcare.domain.models.Medico;
import clinica.medica.vitalcare.domain.models.Pessoa;
import clinica.medica.vitalcare.domain.repositories.MedicoRepository;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.register.UniqueEmailValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    UniqueEmailValidation uniqueEmailValidation;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    PessoaService pessoaService;

    public ResponseEntity<Medico> cadastrar(CadastrarMedicoDto dto) {
        var medico = new Medico(dto);
        medicoRepository.save(medico);
        return new ResponseEntity<>(medico, HttpStatus.CREATED);
    }

    public ResponseEntity<List<ResponseMedicoDto>> listarMedicos() {
        var medicos = medicoRepository.findAllByAtivoTrue();
        var response = medicos.stream().map(m -> {
            return new ResponseMedicoDto(m.getId(), m.getPessoa_id().getNome(), m.getEspecialidade());
        }).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity getMedicoById(Long id) {
        var medico = medicoRepository.findById(id);
        if(medico.isEmpty())
            return new ResponseEntity("Médico com id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);

        var response = new ResponseMedicoDto(id, medico.get().getPessoa_id().getNome(), medico.get().getEspecialidade());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public ResponseEntity editarMedico(Long id, CadastrarMedicoDto dto) {
        var medico = medicoRepository.findById(id);

        if(medico.isEmpty())
            return new ResponseEntity("O médico de id " + id + " não foi encontrado no sistema", HttpStatus.NOT_FOUND);

        var response = medico.get();
        if(dto.pessoa() != null){
            var pessoa = new Pessoa(dto.pessoa());
            response.setPessoa_id(pessoa);
        }

        if(dto.especialidade() != null){
            response.setEspecialidade(dto.especialidade());
        }

        if(dto.crm() != null){
            response.setCrm(dto.crm());
        }

        medicoRepository.save(response);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    public ResponseEntity excluir(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.deletar();
        medicoRepository.save(medico);
        return new ResponseEntity("Médico deletado com sucesso!", HttpStatus.OK);

    }
}

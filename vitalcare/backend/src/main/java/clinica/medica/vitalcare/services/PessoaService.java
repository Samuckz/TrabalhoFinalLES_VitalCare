package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import clinica.medica.vitalcare.domain.dtos.Pessoa.PessoaResponseDto;
import clinica.medica.vitalcare.domain.models.Pessoa;
import clinica.medica.vitalcare.domain.repositories.PessoaRepository;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.RegisterValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    EnderecoService enderecoService;
    @Autowired
    private List<RegisterValidation> validadores;


    @Transactional
    public PessoaResponseDto cadastrarPessoa(CadastrarPessoaDto dto){
        //validadores.forEach(v -> v.validar(dto));
        var pessoa = new Pessoa(dto);
        pessoaRepository.save(pessoa);
        return new PessoaResponseDto(pessoa.getNome(), pessoa.getEmail());
    }
}

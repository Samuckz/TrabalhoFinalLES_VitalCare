package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Pessoa.PessoaResponseDto;
import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import clinica.medica.vitalcare.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    PessoaService pessoaService;

    @PostMapping("cadastrar")
    public ResponseEntity<PessoaResponseDto> cadastrarPessoa(@RequestBody CadastrarPessoaDto dto){
        var pessoa = pessoaService.cadastrarPessoa(dto);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }
}

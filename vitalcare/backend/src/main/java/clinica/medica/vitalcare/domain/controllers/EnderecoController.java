package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Endereco.EnderecoDto;
import clinica.medica.vitalcare.domain.models.Endereco;
import clinica.medica.vitalcare.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @PostMapping("cadastrar")
    public ResponseEntity<EnderecoDto> cadastrarEndereco(@RequestBody EnderecoDto dto) throws Exception {
        return enderecoService.cadastrar(dto);
    }

    @GetMapping("getEnderecos")
    public ResponseEntity<List<Endereco>> getEnderecos(){
        return enderecoService.getEnderecos();
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> getEnderecoByCep(@PathVariable String cep){
        return enderecoService.getEnderecosByCep(cep);
    }

    @PutMapping("atualizarEndereco")
    public ResponseEntity<Endereco> atualizarEndereco(@RequestBody EnderecoDto dto){
        return enderecoService.atualizarEndereco(dto);
    }

    @DeleteMapping("/deletar/{cep}")
    public ResponseEntity<String> deletarEndereco(@PathVariable String cep){
        return enderecoService.deleteEndereco(cep);
    }

}

package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import clinica.medica.vitalcare.domain.dtos.Funcionario.FuncionarioResponseDto;
import clinica.medica.vitalcare.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping("cadastrar")
    public ResponseEntity cadastrarFuncionario(@RequestBody CadastrarFuncionarioDto dto){
        return funcionarioService.cadastrar(dto);
    }

    @GetMapping("listarFuncionarios")
    public ResponseEntity<List<FuncionarioResponseDto>> listarFuncionarios(){
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("funcionario/{id}")
    public ResponseEntity<FuncionarioResponseDto> getFuncionarioById(@PathVariable Long id) throws Exception {
        return funcionarioService.getFuncionarioById(id);
    }
}

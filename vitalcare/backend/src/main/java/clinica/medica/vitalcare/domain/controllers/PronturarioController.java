package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Prontuario.CadastrarProntuarioDto;
import clinica.medica.vitalcare.domain.dtos.Prontuario.ProntuarioResponseDto;
import clinica.medica.vitalcare.services.PronturarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prontuarios")
public class PronturarioController {

    @Autowired
    PronturarioService pronturarioService;
    @PostMapping("cadastrar")
    public ResponseEntity cadastrarProntuario(@RequestBody CadastrarProntuarioDto dto){
        return pronturarioService.cadastrar(dto);
    }

    @GetMapping("listarProntuarios")
    public ResponseEntity<List<ProntuarioResponseDto>> listarProntuarios(){
        return pronturarioService.listarProntuarios();
    }
}

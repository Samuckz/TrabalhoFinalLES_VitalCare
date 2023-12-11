package clinica.medica.vitalcare.domain.controllers;

import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;
import clinica.medica.vitalcare.domain.dtos.Medico.ResponseMedicoDto;
import clinica.medica.vitalcare.domain.models.Medico;
import clinica.medica.vitalcare.services.MedicoService;
import clinica.medica.vitalcare.utils.enums.Especialidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseMedicoDto> cadastrarMedico (@RequestBody CadastrarMedicoDto dto){
        return medicoService.cadastrar(dto);
    }

    @GetMapping("/listarMedicos")
    public ResponseEntity<List<ResponseMedicoDto>> listarMedicos(){
        return medicoService.listarMedicos();
    }

    @GetMapping("/especialidade/{especialidade}")
    public ResponseEntity<List<ResponseMedicoDto>> listarMedicosPorEspecialidade(@PathVariable Especialidade especialidade){
        return medicoService.listarMedicosPorEspecialidade(especialidade);
    }
//
//    @GetMapping("/pesquisar/{id}")
//    public ResponseEntity getMedicoById(@PathVariable Long id){
//        return medicoService.getMedicoById(id);
//    }
//
//    @PutMapping("/editar/{id}")
//    public ResponseEntity editarMedico(@PathVariable Long id, @RequestBody CadastrarMedicoDto dto){
//        return medicoService.editarMedico(id, dto);
//    }
//
//    @DeleteMapping("/excluir/{id}")
//    public ResponseEntity excluirMedico(@PathVariable Long id){
//        return medicoService.excluir(id);
//    }

}

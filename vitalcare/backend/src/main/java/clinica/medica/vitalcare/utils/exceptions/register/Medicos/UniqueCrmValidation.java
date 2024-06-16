package clinica.medica.vitalcare.utils.exceptions.register.Medicos;


import clinica.medica.vitalcare.domain.dtos.Medico.CadastrarMedicoDto;
import clinica.medica.vitalcare.domain.repositories.MedicoRepository;
import clinica.medica.vitalcare.utils.exceptions.register.Funcionario.RegisterValidation;
import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCrmValidation implements RegisterValidationMedico {

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public void validar(CadastrarMedicoDto dto) {
        var medico = medicoRepository.findByCrm(dto.crm());

        if(medico.isPresent()){
            throw new ValidationException("CRM já cadastrado no sistema");
        }
    }
}

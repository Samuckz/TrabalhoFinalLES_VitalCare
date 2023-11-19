package clinica.medica.vitalcare.utils.exceptions.register;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;

import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class CheckPasswordValidation implements RegisterValidation{
    @Override
    public void validar(CadastrarPessoaDto dto) {
        if (!dto.senha().equals(dto.confirmarSenha())){
            throw new ValidationException("As senhas informadas são diferentes");
        }
    }
}

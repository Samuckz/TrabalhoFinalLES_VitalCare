package clinica.medica.vitalcare.utils.exceptions.register;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;

import clinica.medica.vitalcare.utils.exceptions.tratadores.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class PasswordPatternValidation implements RegisterValidation {
    @Override
    public void validar(CadastrarPessoaDto dto) {
        System.out.println(dto.senha() + "\n");

        String senha = dto.senha();

        // Comprimento Mínimo = 8 Caracteres
        if(senha.length() < 8)
            throw new ValidationException("A senha inserida possui menos de 8 caracteres");

        // Caracteres Especiais
        if(senha.matches(".*!@#$%¨&*-_.*"))
            throw new ValidationException("A senha inserida não possui nenhuma caractere especial");

        // Letras maíusculas
        if(!senha.matches(".*[A-Z].*"))
            throw new ValidationException("A senha inserida não possui caracteres maiúsculos");

        // Caracteres minúsculos
        if(!senha.matches(".*[a-z].*"))
            throw new ValidationException("A senha inserida não possui caracteres minúsculos");

        // Caracteres numericos
        if(!senha.matches(".*\\d.*"))
            throw new ValidationException(("A senha inserida não possui caracteres numéricos"));
    }
}

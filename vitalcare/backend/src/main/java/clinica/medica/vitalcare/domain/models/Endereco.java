package clinica.medica.vitalcare.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

}

package clinica.medica.vitalcare.domain.models;

import clinica.medica.vitalcare.domain.dtos.Pessoa.CadastrarPessoaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean ativo;

    public Pessoa(CadastrarPessoaDto dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.cep = dto.cep();
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.estado = dto.estado();
        this.ativo = true;

    }
}

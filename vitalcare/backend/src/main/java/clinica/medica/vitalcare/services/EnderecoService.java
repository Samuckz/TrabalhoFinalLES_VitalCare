package clinica.medica.vitalcare.services;

import clinica.medica.vitalcare.domain.dtos.Endereco.EnderecoDto;
import clinica.medica.vitalcare.domain.models.Endereco;
import clinica.medica.vitalcare.domain.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    @Transactional
    public Endereco verificaEndereco(Endereco endereco){
        var encontrado = enderecoRepository.findByCep(endereco.getCep());

        if(encontrado.isEmpty()) {
            return enderecoRepository.save(endereco);
        }

        return encontrado.get();

    }

    public ResponseEntity<EnderecoDto> cadastrar(EnderecoDto dto) throws Exception {
        var endereco = new Endereco(dto);

        var encontrado = enderecoRepository.findByCep(dto.cep());

        if(encontrado.isPresent())
            throw new Exception("Endereço já cadastrado!");

        enderecoRepository.save(endereco);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Endereco>> getEnderecos() {
        var enderecos = enderecoRepository.findAll();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    public ResponseEntity<Endereco> getEnderecosByCep(String cep) {
        var response = enderecoRepository.findByCep(cep);
        if(response.isEmpty())
            throw new RuntimeException("Endereço não encontrado");


        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteEndereco(String cep) {
        var endereco = enderecoRepository.findByCep(cep);
        if(endereco.isEmpty())
            return new ResponseEntity<>("O Cep informado não foi encontrado no sistema", HttpStatus.NOT_FOUND);

        enderecoRepository.delete(endereco.get());

        return new ResponseEntity<>("Endereço de CEP " + cep + " deletado com sucesso!", HttpStatus.OK);
    }

    public ResponseEntity<Endereco> atualizarEndereco(EnderecoDto dto) {
        var endereco = enderecoRepository.findByCep(dto.cep());
        if(endereco.isEmpty())
            throw new RuntimeException("O Cep informado não foi encontrado no sistema");

        endereco.get().setCidade(dto.cidade());
        endereco.get().setBairro(dto.bairro());
        endereco.get().setEstado(dto.estado());
        endereco.get().setLogradouro(dto.logradouro());

        enderecoRepository.save(endereco.get());

        return new ResponseEntity<>(endereco.get(), HttpStatus.OK);
    }
}

package clinica.medica.vitalcare.domain.models;


import clinica.medica.vitalcare.domain.dtos.Funcionario.CadastrarFuncionarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String senha;

    private Boolean isMedico;

    private Long medicoId;

    public Usuario(CadastrarFuncionarioDto dto, String senhaCriptografada, boolean isMedico, Long IdMedico) {
        this.email = dto.pessoa().email();
        this.senha = senhaCriptografada;
        this.isMedico = isMedico;
        this.medicoId = IdMedico;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(isMedico)
            return java.util.List.of(new SimpleGrantedAuthority("MEDICO"));

        return java.util.List.of(new SimpleGrantedAuthority("FUNCIONARIO"));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

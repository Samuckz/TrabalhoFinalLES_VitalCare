package clinica.medica.vitalcare.domain.controllers;


import clinica.medica.vitalcare.domain.dtos.Login.LoginDto;
import clinica.medica.vitalcare.domain.dtos.Login.LoginResponseDto;
import clinica.medica.vitalcare.domain.models.Usuario;
import clinica.medica.vitalcare.utils.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid LoginDto request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());

        var passwordEncoder = encoder.encode(request.senha());

        System.out.println(usernamePassword.getPrincipal());
        System.out.println(request.senha());
        System.out.println(passwordEncoder);
//        var auth = this.authenticationManager.authenticate(usernamePassword);



        Authentication auth;
        try {
            auth = this.authenticationManager.authenticate(usernamePassword);
        } catch (AuthenticationException e) {
            // Registre a exceção para depuração.
            e.printStackTrace();
            // Ou lance a exceção novamente, se necessário.
            throw e;
        }
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));

    }
}

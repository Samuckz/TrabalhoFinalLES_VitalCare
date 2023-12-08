package clinica.medica.vitalcare.utils.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfiguration{

    @Autowired
    private securityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Autenticação via token
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/funcionarios").hasRole("FUNCIONARIO")
//                        .requestMatchers("/medicos").hasRole("ROLE_MEDICO")
//                        .requestMatchers("/medicos").hasRole("ROLE_FUNCIONARIO")
//                        .requestMatchers("/prontuarios").hasRole("ROLE_FUNCIONARIO")
//                        .requestMatchers("/prontuarios").hasRole("ROLE_MEDICO")
//                        .requestMatchers( "/pacientes").hasRole("ROLE_FUNCIONARIO")
//                        .requestMatchers( "/pacientes").hasRole("ROLE_MEDICO")
//                        .requestMatchers( "/enderecos").hasRole("ROLE_FUNCIONARIO")
//                        .requestMatchers( "/enderecos").hasRole("ROLE_MEDICO")
//                        .requestMatchers( "/agenda").hasRole("ROLE_FUNCIONARIO")
//                        .requestMatchers( "/agenda").hasRole("ROLE_MEDICO")
//                        .requestMatchers("/agendas/agendaMedico/{id}").hasRole("MEDICO")
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll() // Login
                        .requestMatchers(HttpMethod.POST, "/agendas/cadastrar").permitAll() // Agendar Consulta
                        .requestMatchers(HttpMethod.POST, "/endereco/cadastrar").permitAll() // Cadastrar Endereco
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }



//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // Configure your AuthenticationManager here. Neste exemplo, usaremos autenticação em memória.
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password("password")
//                .roles("USER");
//    }




}

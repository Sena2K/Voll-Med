package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //classe de configuração
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean //devolver o objeto para o spring
    //Sem essa anotação no método, o objeto SecurityFilterChain não será exposto como um bean para o Spring.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF, o que pode ser apropriado para APIs.
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a política de sessão para Stateless
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login").permitAll(); // Permite acesso à rota de login
                    req.anyRequest().authenticated();// Requer autenticação para outras rotas
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)//primeiro o meu filtro, dps o filtro do spring
                .build();
    }

    @Bean //o bean serve para exportar uma classe para o spring, fazendo com que ele
    //consiga carrega-la e realize a sua injeção de independencias em outras classes
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

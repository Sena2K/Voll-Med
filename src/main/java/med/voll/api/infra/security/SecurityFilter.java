package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var tokenJWT = recuperarToken(request);
            if(tokenJWT != null){// se o token nao for null ele força a autenticação pro spring, ja que esta liberado
                var subject = tokenService.getSubject(tokenJWT);
                var usuario = repository.findByLogin(subject);
                var auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            // Adicionar o subject ao contexto de segurança ou fazer outras validações se necessário
        } catch (RuntimeException e) {
            // Enviar uma resposta 401 Unauthorized em vez de permitir que o erro cause um 500
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return; // Não prosseguir com a cadeia de filtros
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization"); //verifica se tem o cabeçalho, se nao for null ele pega o token, tira o "bearer" e retorna.
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "").trim(); // Remove "Bearer " do início do token
        }
        return null;

    }
}

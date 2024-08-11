package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenJWT = recuperarToken(request);
            // Processar o token JWT (ex: validação, autenticação, etc.)
        } catch (RuntimeException e) {
            // Caso o token não esteja presente, responda com um erro 401
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return; // Não prossegue para o próximo filtro
        }

        // Prosseguir para o próximo filtro
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token não enviado ou formato inválido");
        }
        return authHeader.substring(7); // Remove "Bearer " do início do token
    }
}

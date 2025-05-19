package aula.microservicos.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.hibernate.annotations.Comment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return request.getServletPath().equals("/seguranca/login");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        doFilter(request, response, filterChain);

        // String token = request.getHeader("JWT-TOKEN");

        // System.out.println("Token = " + token);

        // if (token == null) {
        // response.sendError(403, "Token inexistente");
        // } else {
        // Key key = Keys
        // .hmacShaKeyFor(
        // "minha_chave_super_secreta_para_geracao_do_algoritmo".getBytes(StandardCharsets.UTF_8));
        // Jwts.parserBuilder().setSigningKey(key).build().parse(token);

        // doFilter(request, response, filterChain);

        // }

    }

}

package com.plantilla.manejoUsuarios.security;

import com.plantilla.manejoUsuarios.model.User;
import com.plantilla.manejoUsuarios.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import io.jsonwebtoken.JwtException;

public class RoleValidationFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;
    private final SecretKey secretKey;
    private final String requiredRole;

    public RoleValidationFilter(SecretKey secretKey, String requiredRole) {
        this.secretKey = secretKey;
        this.requiredRole = requiredRole;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.replace("Bearer ", "");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String username = claims.getSubject();
            User user = userRepository.findByUsername(username);
            if (user == null || !user.getRole().name().equals(requiredRole)) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("Acceso denegado: rol insuficiente");
                return;
            }
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Token inválido o expirado");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // Ignorar rutas públicas
        return path.equals("/users/create");
    }
}

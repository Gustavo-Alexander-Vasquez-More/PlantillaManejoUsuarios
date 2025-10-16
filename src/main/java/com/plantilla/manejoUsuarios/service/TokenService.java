/*
En esta clase se implementara la logica de negocio relacionada con los tokens
como la generacion, validacion y renovacion de tokens JWT
*/

package com.plantilla.manejoUsuarios.service;
import com.plantilla.manejoUsuarios.DTO.TokenPayloadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    //Metodo para generar un token JWT
    public String generarToken(TokenPayloadDTO payload){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 40 * 60 * 1000); // 40 minutos en milisegundos
        String token = Jwts.builder()
                .setSubject(payload.getUsername())
                .claim("id", payload.getId())
                .claim("role", payload.getRole())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        return token;
    }

    //Metodo para validar un token JWT
    public boolean validarToken(String token){
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                    .build()
                .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

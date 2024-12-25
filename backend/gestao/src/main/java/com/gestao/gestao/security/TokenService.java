package com.gestao.gestao.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.gestao.gestao.models.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    // Geração do token JWT
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("role", user.getRole())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while creating token", exception);
        }
    }

    // Validação do token JWT
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); // Cria o verificador
            DecodedJWT decodedJWT = verifier.verify(token); // Verifica o token
            return decodedJWT.getSubject(); // Retorna o "subject" (neste caso, o username)
        } catch (JWTVerificationException exception) {
            // Exceção lançada se o token for inválido
            return null;
        }
    }

    // Método auxiliar para gerar a data de expiração
    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC);
    }
}

package com.eduardo.softrestaurant.config;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    // Secret key
    private static final String SECRET_KEY = "my_super_secret_key_12345678987654321";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Generate a token
    public String generateToken(String email, Long employeeId) {
        return Jwts.builder()
                .subject(email)
                .claim("employeeId", employeeId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // expires in 1 hour
                .signWith(getSigningKey())
                .compact();
    }

    // Extract email from token
    public String extractEmployeeName(String token) {
        return parseClaims(token).getPayload().getSubject();
    }

    public Long extractEmployeeId(String token) {
        return parseClaims(token).getPayload().get("employeeId", Long.class);
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token);
    }
}

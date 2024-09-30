package com.todo.todo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenProvider {

    private final byte[] secretKeyBytes;

    public JwtTokenProvider(@Value("${jwtKey}") String secretKey) {
        try {
            this.secretKeyBytes = Hex.decodeHex(secretKey);
        } catch (DecoderException e) {
            throw new IllegalArgumentException("Invalid secret key format. Ensure it is a valid hexadecimal string.", e);
        }
    }

    public String generateToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", userId);

        return createToken(claims);
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    private String createToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 saat
                .signWith(Keys.hmacShaKeyFor(secretKeyBytes), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

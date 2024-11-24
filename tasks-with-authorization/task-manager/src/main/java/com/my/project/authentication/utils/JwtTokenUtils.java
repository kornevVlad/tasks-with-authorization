package com.my.project.authentication.utils;

import com.my.project.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;


@Component
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifeTime;

    /**
     * Генератор токена
     */
    public String generateToken(User user) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifeTime.toMillis());
        return Jwts.builder()
                .claim("role", user.getRole())
                .claim("password", user.getPassword())
                .setSubject(user.getEmail())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * email из token
     */
    public String getEmail(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * role из token
     */
    public String getRoles(String token) {
        return getAllClaimsFromToken(token).get("role", String.class );
    }

    /**
     * password из token
     */
    public String getPassword(String token) {
        return getAllClaimsFromToken(token).get("password", String.class);
    }

    /**
     * Парсинг token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
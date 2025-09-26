package com.example.jwt_study.global.util;

import com.example.jwt_study.global.vo.JwtSecret;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtSecret jwtSecret;
    private static final int KEY_SIZE = 256;
    private SecretKey key;

    @PostConstruct
    public void init(){
        byte[] keyBytes = new byte[KEY_SIZE / 8];
        byte[] decoded = Base64.getDecoder().decode(jwtSecret.getSecret());
        System.arraycopy(decoded, 0, keyBytes, 0, Math.min(decoded.length, keyBytes.length));
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(final String username) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        Date now = new Date();
        long nowMillis = now.getTime();
        long expiredMillis = nowMillis + jwtSecret.getAccessToken();

        return Jwts.builder()
                .issuedAt(now)
                .signWith(key, Jwts.SIG.HS256)
                .claims(claims)
                .expiration(new Date(expiredMillis))
                .compact();
    }

    public String generateRefreshToken(final String username) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        Date now = new Date();
        long nowMillis = now.getTime();
        long expiredMillis = nowMillis + jwtSecret.getRefreshToken();

        return Jwts.builder()
                .issuedAt(now)
                .signWith(key, Jwts.SIG.HS256)
                .claims(claims)
                .expiration(new Date(expiredMillis))
                .compact();
    }

    public Boolean validateToken(String token) {
        Objects.requireNonNull(token);

        Claims claims = getAllClaimsFromToken(token);
        String regeneratedToken = Jwts.builder()
                .signWith(key, Jwts.SIG.HS256)
                .claims(claims)
                .compact();

        return token.equals(regeneratedToken);
    }

    public String getUsernameFromToken(final String token) {

        return getClaimFromToken(token, claims ->
                claims.get("username", String.class)
        );
    }

    public <T> T getClaimFromToken(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long getAccessExpired() {
        return jwtSecret.getAccessToken();
    }

    public Long getRefreshToken() {
        return jwtSecret.getRefreshToken();
    }
}

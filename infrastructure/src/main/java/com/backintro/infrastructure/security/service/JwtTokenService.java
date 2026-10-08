package com.backintro.infrastructure.security.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.model.TokenPrincipal;
import com.backintro.domain.security.port.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService implements TokenService {

    private static final int REFRESH_TOKEN_BYTES = 32;

    private final SecretKey secretKey;
    private final String issuer;
    private final long accessTokenExpirationMillis;
    private final SecureRandom secureRandom = new SecureRandom();

    public JwtTokenService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.issuer}") String issuer,
            @Value("${security.jwt.access-token-expiration}") long accessTokenExpirationMillis
    ) {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length < 32) {
            throw new IllegalArgumentException("JWT secret must contain at least 32 UTF-8 bytes");
        }
        if (accessTokenExpirationMillis <= 0) {
            throw new IllegalArgumentException("JWT access token expiration must be positive");
        }
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.issuer = issuer;
        this.accessTokenExpirationMillis = accessTokenExpirationMillis;
    }

    @Override
    public String generateAccessToken(SecurityUser user) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + accessTokenExpirationMillis);
        List<String> authorities = user.roles().stream()
                .map(Role::authority)
                .sorted()
                .toList();

        return Jwts.builder()
                .issuer(issuer)
                .subject(user.id().toString())
                .claim("email", user.email())
                .claim("roles", authorities)
                .issuedAt(issuedAt)
                .expiration(expiresAt)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String generateRefreshToken() {
        byte[] bytes = new byte[REFRESH_TOKEN_BYTES];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Override
    public TokenPrincipal parseAccessToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .requireIssuer(issuer)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            List<?> rawAuthorities = claims.get("roles", List.class);
            Set<String> authorities = new LinkedHashSet<>();
            if (rawAuthorities != null) {
                rawAuthorities.stream()
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                        .forEach(authorities::add);
            }

            return new TokenPrincipal(
                    UUID.fromString(claims.getSubject()),
                    claims.get("email", String.class),
                    authorities
            );
        } catch (JwtException | IllegalArgumentException exception) {
            throw new InvalidCredentialsException("Invalid or expired access token");
        }
    }
}

package com.backintro.infrastructure.security.service;

import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backintro.domain.security.exception.SecurityDomainException;
import com.backintro.domain.security.port.PasswordService;

@Service
public class BCryptPasswordService implements PasswordService {

    private static final int MAX_BCRYPT_BYTES = 72;

    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String hash(String plainPassword) {
        validatePassword(plainPassword);
        return passwordEncoder.encode(plainPassword);
    }

    @Override
    public boolean matches(String plainPassword, String passwordHash) {
        validatePassword(plainPassword);
        return passwordEncoder.matches(plainPassword, passwordHash);
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new SecurityDomainException("password must not be blank");
        }
        if (password.getBytes(StandardCharsets.UTF_8).length > MAX_BCRYPT_BYTES) {
            throw new SecurityDomainException("password exceeds BCrypt's 72-byte limit");
        }
    }
}

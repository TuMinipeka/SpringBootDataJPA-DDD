package com.backintro.domain.security.port;

import java.util.Optional;
import java.util.UUID;

import com.backintro.domain.security.model.RefreshToken;

public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);

    Optional<RefreshToken> findByToken(String token);

    void deleteByUserId(UUID userId);
}

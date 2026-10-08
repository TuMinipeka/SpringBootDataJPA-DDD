package com.backintro.application.security.usecase;

import com.backintro.domain.security.port.RefreshTokenRepository;

public class LogoutUseCase {

    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUseCase(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void execute(String refreshTokenValue) {
        refreshTokenRepository.findByToken(refreshTokenValue).ifPresent(refreshToken -> {
            refreshToken.revoke();
            refreshTokenRepository.save(refreshToken);
        });
    }
}

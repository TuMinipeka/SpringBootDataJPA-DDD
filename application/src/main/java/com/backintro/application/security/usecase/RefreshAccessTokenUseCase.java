package com.backintro.application.security.usecase;

import java.time.LocalDateTime;

import com.backintro.application.security.command.RefreshAccessTokenCommand;
import com.backintro.application.security.dto.AuthenticationResponse;
import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.model.RefreshToken;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.RefreshTokenRepository;
import com.backintro.domain.security.port.SecurityUserRepository;
import com.backintro.domain.security.port.TokenService;

public class RefreshAccessTokenUseCase {

    private final RefreshTokenRepository refreshTokenRepository;
    private final SecurityUserRepository userRepository;
    private final TokenService tokenService;
    private final long accessTokenExpirationMillis;

    public RefreshAccessTokenUseCase(
            RefreshTokenRepository refreshTokenRepository,
            SecurityUserRepository userRepository,
            TokenService tokenService,
            long accessTokenExpirationMillis
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.accessTokenExpirationMillis = accessTokenExpirationMillis;
    }

    public AuthenticationResponse execute(RefreshAccessTokenCommand command) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(command.refreshToken())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid refresh token"));

        if (!refreshToken.isValidAt(LocalDateTime.now())) {
            throw new InvalidCredentialsException("Refresh token is expired or revoked");
        }

        SecurityUser user = userRepository.findById(refreshToken.userId())
                .filter(SecurityUser::isActive)
                .orElseThrow(InvalidCredentialsException::new);

        return AuthenticationResponse.bearer(
                tokenService.generateAccessToken(user),
                command.refreshToken(),
                accessTokenExpirationMillis / 1_000
        );
    }
}

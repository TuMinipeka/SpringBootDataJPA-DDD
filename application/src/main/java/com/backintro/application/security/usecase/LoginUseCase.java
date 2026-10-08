package com.backintro.application.security.usecase;

import java.time.Duration;
import java.time.LocalDateTime;

import com.backintro.application.security.command.LoginCommand;
import com.backintro.application.security.dto.AuthenticationResponse;
import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.model.RefreshToken;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.PasswordService;
import com.backintro.domain.security.port.RefreshTokenRepository;
import com.backintro.domain.security.port.SecurityUserRepository;
import com.backintro.domain.security.port.TokenService;

public class LoginUseCase {

    private final SecurityUserRepository userRepository;
    private final PasswordService passwordService;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final long accessTokenExpirationMillis;
    private final long refreshTokenExpirationMillis;

    public LoginUseCase(
            SecurityUserRepository userRepository,
            PasswordService passwordService,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            long accessTokenExpirationMillis,
            long refreshTokenExpirationMillis
    ) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenExpirationMillis = accessTokenExpirationMillis;
        this.refreshTokenExpirationMillis = refreshTokenExpirationMillis;
    }

    public AuthenticationResponse execute(LoginCommand command) {
        SecurityUser user = userRepository.findByEmail(command.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!user.isActive() || !passwordService.matches(command.password(), user.passwordHash())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = tokenService.generateAccessToken(user);
        String refreshTokenValue = tokenService.generateRefreshToken();

        RefreshToken refreshToken = RefreshToken.issue(
                user.id(),
                refreshTokenValue,
                LocalDateTime.now().plus(Duration.ofMillis(refreshTokenExpirationMillis))
        );
        refreshTokenRepository.save(refreshToken);

        return AuthenticationResponse.bearer(
                accessToken,
                refreshTokenValue,
                accessTokenExpirationMillis / 1_000
        );
    }
}

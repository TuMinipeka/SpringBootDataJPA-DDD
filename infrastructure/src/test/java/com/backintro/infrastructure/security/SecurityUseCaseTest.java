package com.backintro.infrastructure.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import com.backintro.application.security.command.ChangePasswordCommand;
import com.backintro.application.security.command.LoginCommand;
import com.backintro.application.security.command.RefreshAccessTokenCommand;
import com.backintro.application.security.command.RegisterSecurityUserCommand;
import com.backintro.application.security.usecase.ChangePasswordUseCase;
import com.backintro.application.security.usecase.LoginUseCase;
import com.backintro.application.security.usecase.RefreshAccessTokenUseCase;
import com.backintro.application.security.usecase.RegisterSecurityUserUseCase;
import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.model.RefreshToken;
import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.PasswordService;
import com.backintro.domain.security.port.RefreshTokenRepository;
import com.backintro.domain.security.port.RoleRepository;
import com.backintro.domain.security.port.SecurityUserRepository;
import com.backintro.domain.security.port.TokenService;

class SecurityUseCaseTest {

    @Test
    void registersUserWithHashedPasswordAndDefaultRole() {
        SecurityUserRepository users = mock(SecurityUserRepository.class);
        RoleRepository roles = mock(RoleRepository.class);
        PasswordService passwords = mock(PasswordService.class);
        Role userRole = Role.create("USER", "ROLE_USER");
        when(roles.findByAuthority("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(passwords.hash("safe-password")).thenReturn("hashed-password");
        when(users.save(any(SecurityUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var useCase = new RegisterSecurityUserUseCase(users, roles, passwords);
        var response = useCase.execute(
                new RegisterSecurityUserCommand("USER@example.com", "safe-password")
        );

        assertThat(response.email()).isEqualTo("user@example.com");
        assertThat(response.authorities()).containsExactly("ROLE_USER");
        verify(users).save(any(SecurityUser.class));
    }

    @Test
    void loginReturnsAccessAndRefreshTokens() {
        SecurityUserRepository users = mock(SecurityUserRepository.class);
        PasswordService passwords = mock(PasswordService.class);
        TokenService tokens = mock(TokenService.class);
        RefreshTokenRepository refreshTokens = mock(RefreshTokenRepository.class);
        SecurityUser user = activeUser();
        when(users.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwords.matches("safe-password", user.passwordHash())).thenReturn(true);
        when(tokens.generateAccessToken(user)).thenReturn("access-token");
        when(tokens.generateRefreshToken()).thenReturn("refresh-token");
        when(refreshTokens.save(any(RefreshToken.class))).thenAnswer(invocation -> invocation.getArgument(0));

        var useCase = new LoginUseCase(
                users,
                passwords,
                tokens,
                refreshTokens,
                900_000,
                604_800_000
        );
        var response = useCase.execute(new LoginCommand("user@example.com", "safe-password"));

        assertThat(response.accessToken()).isEqualTo("access-token");
        assertThat(response.refreshToken()).isEqualTo("refresh-token");
        assertThat(response.expiresInSeconds()).isEqualTo(900);
    }

    @Test
    void loginRejectsInvalidPasswordWithoutIssuingTokens() {
        SecurityUserRepository users = mock(SecurityUserRepository.class);
        PasswordService passwords = mock(PasswordService.class);
        TokenService tokens = mock(TokenService.class);
        RefreshTokenRepository refreshTokens = mock(RefreshTokenRepository.class);
        SecurityUser user = activeUser();
        when(users.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwords.matches("wrong-password", user.passwordHash())).thenReturn(false);

        var useCase = new LoginUseCase(
                users,
                passwords,
                tokens,
                refreshTokens,
                900_000,
                604_800_000
        );

        assertThatThrownBy(() -> useCase.execute(new LoginCommand("user@example.com", "wrong-password")))
                .isInstanceOf(InvalidCredentialsException.class);
        verify(tokens, never()).generateAccessToken(any());
    }

    @Test
    void refreshRejectsRevokedToken() {
        RefreshTokenRepository refreshTokens = mock(RefreshTokenRepository.class);
        SecurityUserRepository users = mock(SecurityUserRepository.class);
        TokenService tokens = mock(TokenService.class);
        SecurityUser user = activeUser();
        RefreshToken refreshToken = RefreshToken.issue(
                user.id(),
                "refresh-token",
                LocalDateTime.now().plusDays(1)
        );
        refreshToken.revoke();
        when(refreshTokens.findByToken("refresh-token")).thenReturn(Optional.of(refreshToken));

        var useCase = new RefreshAccessTokenUseCase(refreshTokens, users, tokens, 900_000);

        assertThatThrownBy(() -> useCase.execute(new RefreshAccessTokenCommand("refresh-token")))
                .isInstanceOf(InvalidCredentialsException.class);
        verify(tokens, never()).generateAccessToken(any());
    }

    @Test
    void changingPasswordRevokesSessionsBeforePersistingNewHash() {
        SecurityUserRepository users = mock(SecurityUserRepository.class);
        RefreshTokenRepository refreshTokens = mock(RefreshTokenRepository.class);
        PasswordService passwords = mock(PasswordService.class);
        SecurityUser user = activeUser();
        when(users.findById(user.id())).thenReturn(Optional.of(user));
        when(passwords.matches("old-password", user.passwordHash())).thenReturn(true);
        when(passwords.hash("new-password")).thenReturn("new-hash");

        var useCase = new ChangePasswordUseCase(users, refreshTokens, passwords);
        useCase.execute(new ChangePasswordCommand(user.id(), "old-password", "new-password"));

        assertThat(user.passwordHash()).isEqualTo("new-hash");
        InOrder order = inOrder(refreshTokens, users);
        order.verify(refreshTokens).deleteByUserId(user.id());
        order.verify(users).save(user);
    }

    private SecurityUser activeUser() {
        SecurityUser user = SecurityUser.register("user@example.com", "hashed-password");
        user.assignRole(Role.create("USER", "ROLE_USER"));
        return user;
    }
}

package com.backintro.infrastructure.security.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;

class JwtTokenServiceTest {

    private static final String SECRET =
            "test-secret-with-at-least-thirty-two-bytes-for-hmac-signing";

    @Test
    void generatesAndParsesSignedAccessToken() {
        JwtTokenService service = new JwtTokenService(SECRET, "back-intro-test", 60_000);
        SecurityUser user = SecurityUser.register("User@Example.com", "hashed-password");
        user.assignRole(Role.create("USER", "ROLE_USER"));

        var principal = service.parseAccessToken(service.generateAccessToken(user));

        assertThat(principal.userId()).isEqualTo(user.id());
        assertThat(principal.email()).isEqualTo("user@example.com");
        assertThat(principal.authorities()).containsExactly("ROLE_USER");
    }

    @Test
    void rejectsTokenSignedWithDifferentSecret() {
        JwtTokenService issuer = new JwtTokenService(SECRET, "back-intro-test", 60_000);
        JwtTokenService verifier = new JwtTokenService(
                "another-test-secret-with-at-least-thirty-two-bytes-for-signing",
                "back-intro-test",
                60_000
        );
        SecurityUser user = SecurityUser.register("user@example.com", "hashed-password");

        assertThatThrownBy(() -> verifier.parseAccessToken(issuer.generateAccessToken(user)))
                .isInstanceOf(InvalidCredentialsException.class);
    }

    @Test
    void generatesIndependentRefreshTokens() {
        JwtTokenService service = new JwtTokenService(SECRET, "back-intro-test", 60_000);

        assertThat(Set.of(service.generateRefreshToken(), service.generateRefreshToken()))
                .hasSize(2)
                .allSatisfy(token -> assertThat(token).isNotBlank());
    }
}

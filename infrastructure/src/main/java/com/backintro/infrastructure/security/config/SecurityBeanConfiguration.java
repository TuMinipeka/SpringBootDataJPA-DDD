package com.backintro.infrastructure.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.security.usecase.AssignRoleUseCase;
import com.backintro.application.security.usecase.ChangePasswordUseCase;
import com.backintro.application.security.usecase.GetCurrentSecurityUserUseCase;
import com.backintro.application.security.usecase.LoginUseCase;
import com.backintro.application.security.usecase.LogoutUseCase;
import com.backintro.application.security.usecase.RefreshAccessTokenUseCase;
import com.backintro.application.security.usecase.RegisterSecurityUserUseCase;
import com.backintro.domain.security.port.PasswordService;
import com.backintro.domain.security.port.RefreshTokenRepository;
import com.backintro.domain.security.port.RoleRepository;
import com.backintro.domain.security.port.SecurityUserRepository;
import com.backintro.domain.security.port.TokenService;

@Configuration
public class SecurityBeanConfiguration {

    @Bean
    RegisterSecurityUserUseCase registerSecurityUserUseCase(
            SecurityUserRepository userRepository,
            RoleRepository roleRepository,
            PasswordService passwordService
    ) {
        return new RegisterSecurityUserUseCase(
                userRepository,
                roleRepository,
                passwordService
        );
    }

    @Bean
    LoginUseCase loginUseCase(
            SecurityUserRepository userRepository,
            PasswordService passwordService,
            TokenService tokenService,
            RefreshTokenRepository refreshTokenRepository,
            @Value("${security.jwt.access-token-expiration}") long accessTokenExpirationMillis,
            @Value("${security.jwt.refresh-token-expiration}") long refreshTokenExpirationMillis
    ) {
        return new LoginUseCase(
                userRepository,
                passwordService,
                tokenService,
                refreshTokenRepository,
                accessTokenExpirationMillis,
                refreshTokenExpirationMillis
        );
    }

    @Bean
    RefreshAccessTokenUseCase refreshAccessTokenUseCase(
            RefreshTokenRepository refreshTokenRepository,
            SecurityUserRepository userRepository,
            TokenService tokenService,
            @Value("${security.jwt.access-token-expiration}") long accessTokenExpirationMillis
    ) {
        return new RefreshAccessTokenUseCase(
                refreshTokenRepository,
                userRepository,
                tokenService,
                accessTokenExpirationMillis
        );
    }

    @Bean
    LogoutUseCase logoutUseCase(RefreshTokenRepository refreshTokenRepository) {
        return new LogoutUseCase(refreshTokenRepository);
    }

    @Bean
    GetCurrentSecurityUserUseCase getCurrentSecurityUserUseCase(
            SecurityUserRepository userRepository
    ) {
        return new GetCurrentSecurityUserUseCase(userRepository);
    }

    @Bean
    ChangePasswordUseCase changePasswordUseCase(
            SecurityUserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            PasswordService passwordService
    ) {
        return new ChangePasswordUseCase(
                userRepository,
                refreshTokenRepository,
                passwordService
        );
    }

    @Bean
    AssignRoleUseCase assignRoleUseCase(
            SecurityUserRepository userRepository,
            RoleRepository roleRepository
    ) {
        return new AssignRoleUseCase(userRepository, roleRepository);
    }
}

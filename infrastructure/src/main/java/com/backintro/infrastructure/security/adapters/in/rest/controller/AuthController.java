package com.backintro.infrastructure.security.adapters.in.rest.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backintro.application.security.command.ChangePasswordCommand;
import com.backintro.application.security.command.LoginCommand;
import com.backintro.application.security.command.RefreshAccessTokenCommand;
import com.backintro.application.security.command.RegisterSecurityUserCommand;
import com.backintro.application.security.dto.AuthenticationResponse;
import com.backintro.application.security.dto.SecurityUserResponse;
import com.backintro.application.security.usecase.ChangePasswordUseCase;
import com.backintro.application.security.usecase.GetCurrentSecurityUserUseCase;
import com.backintro.application.security.usecase.LoginUseCase;
import com.backintro.application.security.usecase.LogoutUseCase;
import com.backintro.application.security.usecase.RefreshAccessTokenUseCase;
import com.backintro.application.security.usecase.RegisterSecurityUserUseCase;
import com.backintro.infrastructure.security.adapters.in.rest.dto.ChangePasswordRequest;
import com.backintro.infrastructure.security.adapters.in.rest.dto.LoginRequest;
import com.backintro.infrastructure.security.adapters.in.rest.dto.RefreshTokenRequest;
import com.backintro.infrastructure.security.adapters.in.rest.dto.RegisterSecurityUserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterSecurityUserUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final RefreshAccessTokenUseCase refreshUseCase;
    private final LogoutUseCase logoutUseCase;
    private final GetCurrentSecurityUserUseCase getCurrentUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    public AuthController(
            RegisterSecurityUserUseCase registerUseCase,
            LoginUseCase loginUseCase,
            RefreshAccessTokenUseCase refreshUseCase,
            LogoutUseCase logoutUseCase,
            GetCurrentSecurityUserUseCase getCurrentUserUseCase,
            ChangePasswordUseCase changePasswordUseCase
    ) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.refreshUseCase = refreshUseCase;
        this.logoutUseCase = logoutUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<SecurityUserResponse> register(
            @Valid @RequestBody RegisterSecurityUserRequest request
    ) {
        SecurityUserResponse response = registerUseCase.execute(
                new RegisterSecurityUserCommand(request.email(), request.password())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
                loginUseCase.execute(new LoginCommand(request.email(), request.password()))
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        return ResponseEntity.ok(
                refreshUseCase.execute(new RefreshAccessTokenCommand(request.refreshToken()))
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        logoutUseCase.execute(request.refreshToken());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<SecurityUserResponse> me(Authentication authentication) {
        return ResponseEntity.ok(
                getCurrentUserUseCase.execute(principalId(authentication))
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {
        changePasswordUseCase.execute(
                new ChangePasswordCommand(
                        principalId(authentication),
                        request.currentPassword(),
                        request.newPassword()
                )
        );
        return ResponseEntity.noContent().build();
    }

    private UUID principalId(Authentication authentication) {
        return (UUID) authentication.getPrincipal();
    }
}

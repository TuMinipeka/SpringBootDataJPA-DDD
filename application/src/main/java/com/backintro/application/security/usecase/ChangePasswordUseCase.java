package com.backintro.application.security.usecase;

import com.backintro.application.security.command.ChangePasswordCommand;
import com.backintro.domain.security.exception.InvalidCredentialsException;
import com.backintro.domain.security.exception.SecurityUserNotFoundException;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.PasswordService;
import com.backintro.domain.security.port.RefreshTokenRepository;
import com.backintro.domain.security.port.SecurityUserRepository;

public class ChangePasswordUseCase {

    private final SecurityUserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordService passwordService;

    public ChangePasswordUseCase(
            SecurityUserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            PasswordService passwordService
    ) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordService = passwordService;
    }

    public void execute(ChangePasswordCommand command) {
        SecurityUser user = userRepository.findById(command.userId())
                .orElseThrow(() -> new SecurityUserNotFoundException(command.userId()));

        if (!passwordService.matches(command.currentPassword(), user.passwordHash())) {
            throw new InvalidCredentialsException("Current password is invalid");
        }

        user.changePasswordHash(passwordService.hash(command.newPassword()));
        refreshTokenRepository.deleteByUserId(user.id());
        userRepository.save(user);
    }
}

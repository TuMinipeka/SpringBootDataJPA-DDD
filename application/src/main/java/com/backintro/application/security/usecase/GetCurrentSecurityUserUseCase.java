package com.backintro.application.security.usecase;

import java.util.UUID;

import com.backintro.application.security.dto.SecurityUserResponse;
import com.backintro.domain.security.exception.SecurityUserNotFoundException;
import com.backintro.domain.security.port.SecurityUserRepository;

public class GetCurrentSecurityUserUseCase {

    private final SecurityUserRepository userRepository;

    public GetCurrentSecurityUserUseCase(SecurityUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SecurityUserResponse execute(UUID userId) {
        return userRepository.findById(userId)
                .map(SecurityUserResponse::from)
                .orElseThrow(() -> new SecurityUserNotFoundException(userId));
    }
}

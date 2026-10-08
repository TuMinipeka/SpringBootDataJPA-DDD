package com.backintro.application.security.usecase;

import com.backintro.application.security.command.RegisterSecurityUserCommand;
import com.backintro.application.security.dto.SecurityUserResponse;
import com.backintro.domain.security.exception.SecurityDomainException;
import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.PasswordService;
import com.backintro.domain.security.port.RoleRepository;
import com.backintro.domain.security.port.SecurityUserRepository;

public class RegisterSecurityUserUseCase {

    private static final String DEFAULT_AUTHORITY = "ROLE_USER";

    private final SecurityUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordService passwordService;

    public RegisterSecurityUserUseCase(
            SecurityUserRepository userRepository,
            RoleRepository roleRepository,
            PasswordService passwordService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordService = passwordService;
    }

    public SecurityUserResponse execute(RegisterSecurityUserCommand command) {
        if (command.email() == null || command.email().isBlank()) {
            throw new SecurityDomainException("email must not be blank");
        }
        String normalizedEmail = command.email().trim().toLowerCase(java.util.Locale.ROOT);
        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new SecurityDomainException("A security user with that email already exists");
        }

        Role userRole = roleRepository.findByAuthority(DEFAULT_AUTHORITY)
                .orElseThrow(() -> new SecurityDomainException("Default security role is not configured"));

        SecurityUser user = SecurityUser.register(
                normalizedEmail,
                passwordService.hash(command.password())
        );
        user.assignRole(userRole);

        return SecurityUserResponse.from(userRepository.save(user));
    }
}

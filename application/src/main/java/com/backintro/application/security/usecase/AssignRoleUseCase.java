package com.backintro.application.security.usecase;

import com.backintro.application.security.command.AssignRoleCommand;
import com.backintro.application.security.dto.SecurityUserResponse;
import com.backintro.domain.security.exception.SecurityDomainException;
import com.backintro.domain.security.exception.SecurityUserNotFoundException;
import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.RoleRepository;
import com.backintro.domain.security.port.SecurityUserRepository;

public class AssignRoleUseCase {

    private final SecurityUserRepository userRepository;
    private final RoleRepository roleRepository;

    public AssignRoleUseCase(
            SecurityUserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public SecurityUserResponse execute(AssignRoleCommand command) {
        SecurityUser user = userRepository.findById(command.userId())
                .orElseThrow(() -> new SecurityUserNotFoundException(command.userId()));

        String roleName = command.roleName().trim();
        Role role = roleRepository.findByName(roleName)
                .or(() -> roleRepository.findByAuthority(roleName))
                .orElseThrow(() -> new SecurityDomainException("Security role not found: " + command.roleName()));

        user.assignRole(role);
        return SecurityUserResponse.from(userRepository.save(user));
    }
}

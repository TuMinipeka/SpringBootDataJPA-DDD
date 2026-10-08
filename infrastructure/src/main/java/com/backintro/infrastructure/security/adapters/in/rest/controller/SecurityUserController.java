package com.backintro.infrastructure.security.adapters.in.rest.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backintro.application.security.command.AssignRoleCommand;
import com.backintro.application.security.dto.SecurityUserResponse;
import com.backintro.application.security.usecase.AssignRoleUseCase;
import com.backintro.infrastructure.security.adapters.in.rest.dto.AssignRoleRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/security/users")
public class SecurityUserController {

    private final AssignRoleUseCase assignRoleUseCase;

    public SecurityUserController(AssignRoleUseCase assignRoleUseCase) {
        this.assignRoleUseCase = assignRoleUseCase;
    }

    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SecurityUserResponse> assignRole(
            @PathVariable UUID userId,
            @Valid @RequestBody AssignRoleRequest request
    ) {
        return ResponseEntity.ok(
                assignRoleUseCase.execute(new AssignRoleCommand(userId, request.roleName()))
        );
    }
}

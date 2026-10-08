package com.backintro.application.security.command;

public record RegisterSecurityUserCommand(
        String email,
        String password
) {
}

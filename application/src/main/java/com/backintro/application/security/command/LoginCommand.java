package com.backintro.application.security.command;

public record LoginCommand(
        String email,
        String password
) {
}

package com.backintro.application.consenttype.command;

import java.util.Objects;

public record RegisterConsentTypeCommand(
        String name,
        String code,
        String description
) {

    public RegisterConsentTypeCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}

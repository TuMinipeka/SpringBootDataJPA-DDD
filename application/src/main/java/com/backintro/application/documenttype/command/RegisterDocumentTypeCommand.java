package com.backintro.application.documenttype.command;

import java.util.Objects;

public record RegisterDocumentTypeCommand(
        String name,
        String code
) {

    public RegisterDocumentTypeCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}

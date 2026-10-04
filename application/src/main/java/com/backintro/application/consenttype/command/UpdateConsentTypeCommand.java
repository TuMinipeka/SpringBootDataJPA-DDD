package com.backintro.application.consenttype.command;

import java.util.Objects;

import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;

public record UpdateConsentTypeCommand(
        ConsentTypeId id,
        String name,
        String code,
        String description
) {

    public UpdateConsentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}

package com.backintro.application.professionaltype.command;

import java.util.Objects;

import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record UpdateProfessionalTypeCommand(
        ProfessionalTypeId id,
        String name
) {

    public UpdateProfessionalTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}

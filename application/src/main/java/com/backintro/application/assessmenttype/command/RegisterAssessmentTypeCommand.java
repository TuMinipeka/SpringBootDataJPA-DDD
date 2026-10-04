package com.backintro.application.assessmenttype.command;

import java.util.Objects;

public record RegisterAssessmentTypeCommand(
        String name,
        String code,
        String description
) {

    public RegisterAssessmentTypeCommand {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}

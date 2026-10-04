package com.backintro.application.assessmenttype.command;

import java.util.Objects;

import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public record UpdateAssessmentTypeCommand(
        AssessmentTypeId id,
        String name,
        String code,
        String description
) {

    public UpdateAssessmentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(code, "code must not be null");
    }
}

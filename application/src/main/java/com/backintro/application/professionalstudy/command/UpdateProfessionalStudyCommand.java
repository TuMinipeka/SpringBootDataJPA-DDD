package com.backintro.application.professionalstudy.command;

import java.util.Objects;

import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public record UpdateProfessionalStudyCommand(
        ProfessionalStudyId id,
        String title,
        String university,
        boolean valid,
        String resolutionNumber
) {

    public UpdateProfessionalStudyCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
    }
}

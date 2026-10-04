package com.backintro.application.professionalstudy.command;

import java.util.Objects;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.study.model.valueobject.StudyId;

public record RegisterProfessionalStudyCommand(
        StudyId studyId,
        ProfessionalId professionalId,
        String title,
        String university,
        String resolutionNumber,
        CountryId countryId
) {

    public RegisterProfessionalStudyCommand {
        Objects.requireNonNull(studyId, "studyId must not be null");
        Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        Objects.requireNonNull(title, "title must not be null");
    }
}

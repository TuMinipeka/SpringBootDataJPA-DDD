package com.backintro.domain.common.exception;

import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public class ProfessionalStudyNotFoundException extends RuntimeException {

    public ProfessionalStudyNotFoundException(ProfessionalStudyId id) {
        super("Professional study not found with id: " + id.value());
    }
}

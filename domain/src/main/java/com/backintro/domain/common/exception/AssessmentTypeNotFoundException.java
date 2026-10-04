package com.backintro.domain.common.exception;

import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public class AssessmentTypeNotFoundException extends RuntimeException {

    public AssessmentTypeNotFoundException(AssessmentTypeId id) {
        super("Assessment type not found with id: " + id.value());
    }
}

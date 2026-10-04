package com.backintro.domain.common.exception;

import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class ProfessionalTypeNotFoundException extends RuntimeException {

    public ProfessionalTypeNotFoundException(ProfessionalTypeId id) {
        super("Professional type not found with id: " + id.value());
    }
}

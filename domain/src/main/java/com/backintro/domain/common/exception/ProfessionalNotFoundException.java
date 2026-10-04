package com.backintro.domain.common.exception;

import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public class ProfessionalNotFoundException extends RuntimeException {

    public ProfessionalNotFoundException(ProfessionalId id) {
        super("Professional not found with id: " + id.value());
    }
}

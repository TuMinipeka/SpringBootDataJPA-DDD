package com.backintro.domain.common.exception;

import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;

public class ConsentTypeNotFoundException extends RuntimeException {

    public ConsentTypeNotFoundException(ConsentTypeId id) {
        super("Consent type not found with id: " + id.value());
    }
}

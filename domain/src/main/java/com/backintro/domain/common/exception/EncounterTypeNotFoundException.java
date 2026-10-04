package com.backintro.domain.common.exception;

import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;

public class EncounterTypeNotFoundException extends RuntimeException {

    public EncounterTypeNotFoundException(EncounterTypeId id) {
        super("Encounter type not found with id: " + id.value());
    }
}

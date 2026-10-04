package com.backintro.domain.common.exception;

import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public class EncounterStatusNotFoundException extends RuntimeException {

    public EncounterStatusNotFoundException(EncounterStatusId id) {
        super("Encounter status not found with id: " + id.value());
    }
}

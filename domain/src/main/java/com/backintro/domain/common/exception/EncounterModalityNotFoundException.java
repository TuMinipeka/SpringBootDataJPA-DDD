package com.backintro.domain.common.exception;

import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;

public class EncounterModalityNotFoundException extends RuntimeException {

    public EncounterModalityNotFoundException(EncounterModalityId id) {
        super("Encounter modality not found with id: " + id.value());
    }
}

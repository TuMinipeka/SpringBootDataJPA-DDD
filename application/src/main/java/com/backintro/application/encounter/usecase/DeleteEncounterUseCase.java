package com.backintro.application.encounter.usecase;

import java.time.LocalDateTime;

import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.domain.encounter.event.EncounterDeletedEvent;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encounter.port.repository.EncounterRepository;

public class DeleteEncounterUseCase {

    private final EncounterRepository encounterRepository;

    public DeleteEncounterUseCase(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    public EncounterDeletedEvent execute(EncounterId id) {
        var encounter = encounterRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        encounterRepository.delete(encounter);

        return new EncounterDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}

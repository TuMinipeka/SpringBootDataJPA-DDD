package com.backintro.application.encountermodality.usecase;

import java.time.LocalDateTime;

import com.backintro.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.backintro.domain.encountermodality.event.EncounterModalityDeletedEvent;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;

public class DeleteEncounterModalityUseCase {

    private final EncounterModalityRepository encounterModalityRepository;

    public DeleteEncounterModalityUseCase(
            EncounterModalityRepository encounterModalityRepository
    ) {
        this.encounterModalityRepository = encounterModalityRepository;
    }

    public EncounterModalityDeletedEvent execute(EncounterModalityId id) {
        var encounterModality = encounterModalityRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterModalityNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        encounterModalityRepository.delete(encounterModality);

        return new EncounterModalityDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}

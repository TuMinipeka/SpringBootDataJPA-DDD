package com.backintro.application.encounterstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import com.backintro.domain.encounterstatus.event.EncounterStatusDeletedEvent;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class DeleteEncounterStatusUseCase {

    private final EncounterStatusRepository encounterStatusRepository;

    public DeleteEncounterStatusUseCase(
            EncounterStatusRepository encounterStatusRepository
    ) {
        this.encounterStatusRepository = encounterStatusRepository;
    }

    public EncounterStatusDeletedEvent execute(EncounterStatusId id) {
        var encounterStatus = encounterStatusRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        encounterStatusRepository.delete(encounterStatus);

        return new EncounterStatusDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}

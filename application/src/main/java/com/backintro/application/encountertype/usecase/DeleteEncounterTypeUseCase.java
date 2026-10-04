package com.backintro.application.encountertype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.backintro.domain.encountertype.event.EncounterTypeDeletedEvent;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;

public class DeleteEncounterTypeUseCase {

    private final EncounterTypeRepository encounterTypeRepository;

    public DeleteEncounterTypeUseCase(
            EncounterTypeRepository encounterTypeRepository
    ) {
        this.encounterTypeRepository = encounterTypeRepository;
    }

    public EncounterTypeDeletedEvent execute(EncounterTypeId id) {
        var encounterType = encounterTypeRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        encounterTypeRepository.delete(encounterType);

        return new EncounterTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}

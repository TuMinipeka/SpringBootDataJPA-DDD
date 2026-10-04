package com.backintro.application.encounter.usecase;

import com.backintro.application.encounter.dto.EncounterResponse;
import com.backintro.application.encounter.exception.EncounterNotFoundApplicationException;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encounter.port.repository.EncounterRepository;

public class GetEncounterByIdUseCase {

    private final EncounterRepository encounterRepository;

    public GetEncounterByIdUseCase(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    public EncounterResponse execute(EncounterId id) {
        var encounter = encounterRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new EncounterResponse(
                encounter.id().value(),
                encounter.startedAt(),
                encounter.endedAt(),
                encounter.reasonForVisit(),
                encounter.currentCondition()
        );
    }
}

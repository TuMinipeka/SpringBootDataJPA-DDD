package com.backintro.application.encountermodality.usecase;

import com.backintro.application.encountermodality.dto.EncounterModalityResponse;
import com.backintro.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;

public class GetEncounterModalityByIdUseCase {

    private final EncounterModalityRepository encounterModalityRepository;

    public GetEncounterModalityByIdUseCase(
            EncounterModalityRepository encounterModalityRepository
    ) {
        this.encounterModalityRepository = encounterModalityRepository;
    }

    public EncounterModalityResponse execute(EncounterModalityId id) {
        var encounterModality = encounterModalityRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterModalityNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new EncounterModalityResponse(
                encounterModality.id().value(),
                encounterModality.name(),
                encounterModality.code()
        );
    }
}

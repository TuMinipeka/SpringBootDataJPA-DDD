package com.backintro.application.encountertype.usecase;

import com.backintro.application.encountertype.dto.EncounterTypeResponse;
import com.backintro.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;

public class GetEncounterTypeByIdUseCase {

    private final EncounterTypeRepository encounterTypeRepository;

    public GetEncounterTypeByIdUseCase(
            EncounterTypeRepository encounterTypeRepository
    ) {
        this.encounterTypeRepository = encounterTypeRepository;
    }

    public EncounterTypeResponse execute(EncounterTypeId id) {
        var encounterType = encounterTypeRepository.findById(id)
                .orElseThrow(() ->
                        new EncounterTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new EncounterTypeResponse(
                encounterType.id().value(),
                encounterType.name(),
                encounterType.code()
        );
    }
}
